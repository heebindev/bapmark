package com.heebin.bapmark.controller;

import com.heebin.bapmark.dto.TokenRequest;
import com.heebin.bapmark.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody TokenRequest tokenRequest) {
        String jwt = authService.loginWithGoogle(tokenRequest.getIdToken());

        // 프론트에게 accessToken처럼 전달
        return ResponseEntity.ok().body(Map.of("accessToken", jwt));
    }

}
