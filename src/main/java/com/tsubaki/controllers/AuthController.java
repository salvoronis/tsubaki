package com.tsubaki.controllers;


import com.tsubaki.dto.auth.LoginRequestDto;
import com.tsubaki.dto.auth.RegisterRequestDto;
import com.tsubaki.dto.auth.TokenDto;
import com.tsubaki.exceptions.AlreadyTakenException;
import com.tsubaki.security.jwt.JwtUtils;
import com.tsubaki.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private JwtUtils jwtUtils;

    private final AuthService authService;

    @Autowired
    public AuthController(
            JwtUtils jwtUtils,
            AuthService authService) {
        this.jwtUtils = jwtUtils;
        this.authService = authService;
    }

    @PostMapping("/singin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(
                authService.singIn(loginRequestDto)
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenDto tokenDto) {
        boolean isRequiredRefresh = jwtUtils.expiredInLimitTime(tokenDto.getToken());
        if (isRequiredRefresh) {
            String username = jwtUtils.getUserNameFromJwtTokenn(tokenDto.getToken());
            String token = jwtUtils.generateJwtToken(username);
            tokenDto.setToken(token);
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/singup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        try {
            authService.register(registerRequestDto);
            return ResponseEntity.ok().build();
        } catch (AlreadyTakenException e) {
            return ResponseEntity.status(e.getStatus()).build(); //TODO errors body
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
