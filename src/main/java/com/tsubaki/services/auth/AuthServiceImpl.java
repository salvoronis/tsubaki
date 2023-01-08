package com.tsubaki.services.auth;

import com.tsubaki.dto.LoginRequestDto;
import com.tsubaki.dto.LoginResponseDto;
import com.tsubaki.dto.RegisterRequestDto;
import com.tsubaki.exceptions.AlreadyTakenException;
import com.tsubaki.exceptions.GlobalError;
import com.tsubaki.models.User;
import com.tsubaki.models.role.ERole;
import com.tsubaki.models.role.Role;
import com.tsubaki.repositories.RoleRepository;
import com.tsubaki.repositories.UserRepository;
import com.tsubaki.security.jwt.JwtUtils;
import com.tsubaki.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder encoder,
            JwtUtils jwtUtils,
            RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
    }

    @Override
    public LoginResponseDto singIn(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new LoginResponseDto(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public void register(RegisterRequestDto registerRequestDto) throws AlreadyTakenException {
        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            throw new AlreadyTakenException(GlobalError.USERNAME_ALREADY_TAKEN);
        }
        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new AlreadyTakenException(GlobalError.EMAIL_ALREADY_TAKEN);
        }

        Set<Role> userRoles = new HashSet<>();

        Role userRole = roleRepository
                .findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));

        userRoles.add(userRole);

        User user = new User(
                registerRequestDto.getUsername(),
                registerRequestDto.getEmail(),
                encoder.encode(registerRequestDto.getPassword()),
                userRoles);

        userRepository.save(user);
    }

}
