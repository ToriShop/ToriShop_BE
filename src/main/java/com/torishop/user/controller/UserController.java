package com.torishop.user.controller;

import com.torishop.authentication.JwtUtil;
import com.torishop.user.dto.LoginRequest;
import com.torishop.user.dto.User;
import com.torishop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    ResponseEntity<String> loginUser(@RequestBody LoginRequest request) throws BadCredentialsException {
        User user = userService.loginUser(request);
        return ResponseEntity.ok(jwtUtil.createJwt(user.getUsername(), Arrays.asList(user.getUserRole().getValue())));
    }

}
