package com.tsubaki.controllers;

import com.tsubaki.exceptions.NoSuchUserException;
import com.tsubaki.security.services.UserDetailsImpl;
import com.tsubaki.services.greed.GreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/greed")
@RequiredArgsConstructor
public class GreedController {

    private final GreedService greedService;

    @GetMapping("/items")
    public ResponseEntity<?> getMenu() {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok(greedService.getGreedItems(userDetails.getId()));
    }
}
