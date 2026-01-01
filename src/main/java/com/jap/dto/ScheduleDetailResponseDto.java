package com.jap.dto;

import com.jap.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScheduleDetailResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 댓글 목록
    private List<CommentResponseDto> comments;

    public ScheduleDetailResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.author = schedule.getAuthor();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        this.comments = schedule.getComments().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
