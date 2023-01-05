package com.tsubaki.controllers;


import com.tsubaki.dto.LoginRequestDto;
import com.tsubaki.dto.LoginResponseDto;
import com.tsubaki.dto.RegisterRequestDto;
import com.tsubaki.models.User;
import com.tsubaki.models.role.ERole;
import com.tsubaki.models.role.Role;
import com.tsubaki.repositories.RoleRepository;
import com.tsubaki.repositories.UserRepository;
import com.tsubaki.security.jwt.JwtUtils;
import com.tsubaki.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/singin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
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
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new LoginResponseDto(
                        jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles)
        );
    }

    @PostMapping("/singup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken");
        }
        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use");
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

        return ResponseEntity.ok().build();
    }
}
