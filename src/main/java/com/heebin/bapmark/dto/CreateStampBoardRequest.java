package com.heebin.bapmark.dto;

import lombok.Data;

@Data
public class CreateStampBoardRequest {
    private String email;
    private String title;
    private String description;
    private String backgroundColor;
}
