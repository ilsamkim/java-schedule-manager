package com.jap.service;

import com.jap.dto.CommentResponseDto;
import com.jap.entity.Comment;
import com.jap.entity.Schedule;
import com.jap.repository.CommentRepository;
import com.jap.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto createComment(
            Long scheduleId,
            String content,
            String author,
            String password
    ) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));

        // 댓글 10개 제한
        int commentCount = commentRepository.countBySchedule(schedule);
        if (commentCount >= 10) {
            throw new IllegalArgumentException("댓글은 최대 10개까지 작성할 수 있습니다.");
        }

        Comment comment = new Comment(content, author, password, schedule);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }
}
