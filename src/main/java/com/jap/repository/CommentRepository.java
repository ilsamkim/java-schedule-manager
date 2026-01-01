package com.jap.repository;

import com.jap.entity.Comment;
import com.jap.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    int countBySchedule(Schedule schedule);
}
