package com.heebin.bapmark.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(name = "oauth_id")
    private String oauthId;

    // 기본 생성자 (JPA 필수)
    public User() {}

    // email + oauthId 생성자
    public User(String email, String oauthId) {
        this.email = email;
        this.oauthId = oauthId;
    }

    // getter, setter
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }
}
