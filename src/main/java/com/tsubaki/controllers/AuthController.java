package com.tsubaki.controllers;


import com.tsubaki.dto.LoginRequestDto;
import com.tsubaki.dto.RegisterRequestDto;
import com.tsubaki.exceptions.AlreadyTakenException;
import com.tsubaki.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/singin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(
                authService.singIn(loginRequestDto)
        );
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
