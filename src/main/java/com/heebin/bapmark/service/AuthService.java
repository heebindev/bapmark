package com.heebin.bapmark.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.heebin.bapmark.entity.User;
import com.heebin.bapmark.repository.UserRepository;
import com.heebin.bapmark.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // ✅ 생성자 하나만
    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // ✅ 로그인 로직 하나만!
    public String loginWithGoogle(String idTokenString) {
        try {
            // 1. Google ID Token 검증
            GoogleIdToken.Payload payload = verifyGoogleToken(idTokenString);
            String email = payload.getEmail();
            System.out.println("✅ 검증된 이메일: " + email);

            // 2. 사용자 조회 & 없으면 생성
            Optional<User> existingUser = userRepository.findByEmail(email);
            if (existingUser.isEmpty()) {
                User newUser = new User();
                newUser.setEmail(email);
                userRepository.save(newUser);
            }

            // 3. JWT 발급
            return jwtUtil.generateToken(email);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Google 로그인 중 오류 발생: " + e.getMessage(), e);
        }
    }

    // ✅ ID Token 검증 메서드
    private GoogleIdToken.Payload verifyGoogleToken(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                    .Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList("407408718192.apps.googleusercontent.com")) // 내 프로젝트 Client ID
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                return idToken.getPayload();
            } else {
                throw new IllegalArgumentException("유효하지 않은 Google ID Token입니다.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Google 로그인 실패", e);
        }
    }
}
