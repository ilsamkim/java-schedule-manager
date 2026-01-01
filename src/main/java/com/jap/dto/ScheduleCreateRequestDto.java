package com.jap.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleCreateRequestDto {

    private String title;
    private String content;
    private String author;
    private String password;
}
