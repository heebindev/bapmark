package com.heebin.bapmark.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class StampBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // 배경 색상 (선택)
    private String backgroundColor;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 생성일
    private LocalDateTime createdAt = LocalDateTime.now();

    public void setUser(User user) {
    }

    public void setTitle(String title) {
    }

    public void setDescription(String description) {
    }

    public void setBackgroundColor(String backgroundColor) {
    }
}
