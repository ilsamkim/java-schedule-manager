package com.jap.repository;

import com.jap.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 작성자명으로 조회, 수정일 내림차순
    List<Schedule> findByAuthorOrderByUpdatedAtDesc(String author);

    // 전체 조회, 수정일 내림차순
    List<Schedule> findAllByOrderByUpdatedAtDesc();
}
