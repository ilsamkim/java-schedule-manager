package com.jap.dto;

import lombok.Getter;

@Getter
public class CommentCreateRequestDto {

    private String content;
    private String author;
    private String password;
}
