package com.jap.controller;

import com.jap.dto.CommentCreateRequestDto;
import com.jap.dto.CommentResponseDto;
import com.jap.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CommentCreateRequestDto requestDto
    ) {
        CommentResponseDto responseDto = commentService.createComment(
                scheduleId,
                requestDto.getContent(),
                requestDto.getAuthor(),
                requestDto.getPassword()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
