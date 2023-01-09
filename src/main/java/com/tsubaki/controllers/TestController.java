package com.tsubaki.controllers;

import com.tsubaki.converters.MenuItemToToolbarResponseDto;
import com.tsubaki.converters.UserToUserDto;
import com.tsubaki.dto.UserDto;
import com.tsubaki.dto.toolbar.ToolbarResponseDto;
import com.tsubaki.repositories.MenuItemRepository;
import com.tsubaki.repositories.UserRepository;
import com.tsubaki.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    private final UserRepository userRepository;

    private final UserToUserDto userToUserDto;

    private final MenuItemRepository menuItemRepository;

    private final MenuItemToToolbarResponseDto menuItemToToolbarResponseDto;

    @Autowired
    public TestController(
            UserRepository userRepository,
            UserToUserDto userToUserDto,
            MenuItemRepository menuItemRepository,
            MenuItemToToolbarResponseDto menuItemToToolbarResponseDto) {
        this.userRepository = userRepository;
        this.userToUserDto = userToUserDto;
        this.menuItemRepository = menuItemRepository;
        this.menuItemToToolbarResponseDto = menuItemToToolbarResponseDto;
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Set<ToolbarResponseDto> result = new HashSet<>();
        userRepository.findAll().forEach(
                user -> user.getUserMenuItems().forEach( item ->
                        result.add(menuItemToToolbarResponseDto.transform(item))));

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
