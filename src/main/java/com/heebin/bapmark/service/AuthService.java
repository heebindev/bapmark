package com.heebin.bapmark.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.heebin.bapmark.entity.User;
import com.heebin.bapmark.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginWithGoogle(String idToken) {
        // 구글 토큰 검증
        GoogleIdToken.Payload payload = verifyGoogleToken(idToken);

        String email = payload.getEmail();
        String oauthId = payload.getSubject(); // 고유한 Google 사용자 ID

        return userRepository.findByOauthId(oauthId)
                .orElseGet(() -> userRepository.save(new User(email, oauthId)));
    }

    private GoogleIdToken.Payload verifyGoogleToken(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                    .Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList("259615437161-8u2923f0uqb79qtgnodukisb9bncl6gi.apps.googleusercontent.com")) // Google Cloud에서 발급받은 Client ID
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