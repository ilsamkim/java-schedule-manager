package com.jap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateRequestDto {

    @NotBlank(message = "댓글 내용은 필수값입니다.")
    @Size(max = 100, message = "댓글 내용은 최대 100자까지 가능합니다.")
    private String content;

    @NotBlank(message = "작성자명은 필수값입니다.")
    private String author;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;
}
