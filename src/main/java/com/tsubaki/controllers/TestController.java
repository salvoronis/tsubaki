package com.tsubaki.controllers;

import com.tsubaki.converters.UserToUserDto;
import com.tsubaki.dto.UserDto;
import com.tsubaki.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    private final UserRepository userRepository;

    private final UserToUserDto userToUserDto;

    @Autowired
    public TestController(
            UserRepository userRepository,
            UserToUserDto userToUserDto) {
        this.userRepository = userRepository;
        this.userToUserDto = userToUserDto;
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> users = new ArrayList<>();
            userRepository.findAll().forEach(
                    user -> users.add(userToUserDto.transform(user)));
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
