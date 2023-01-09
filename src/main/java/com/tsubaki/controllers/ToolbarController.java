package com.tsubaki.controllers;

import com.tsubaki.exceptions.NoSuchUserException;
import com.tsubaki.security.services.UserDetailsImpl;
import com.tsubaki.services.toolbar.ToolbarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/toolbar")
@RequiredArgsConstructor
public class ToolbarController {

    private final ToolbarService toolbarService;

    @GetMapping("/menu")
    public ResponseEntity<?> getMenu() {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            return ResponseEntity.ok(toolbarService.getMenu(userDetails.getUsername()));
        } catch (NoSuchUserException e) {
            return ResponseEntity.status(e.getStatus()).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
