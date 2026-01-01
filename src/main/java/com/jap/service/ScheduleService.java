package com.jap.service;

import com.jap.dto.ScheduleResponseDto;
import com.jap.entity.Schedule;
import com.jap.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    public Schedule createSchedule(String title, String content, String author, String password) {
        Schedule schedule = new Schedule(title, content, author, password);
        return scheduleRepository.save(schedule);
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> getSchedules(String author) {

        List<Schedule> schedules;

        if (author == null) {
            schedules = scheduleRepository.findAllByOrderByUpdatedAtDesc();
        } else {
            schedules = scheduleRepository.findByAuthorOrderByUpdatedAtDesc(author);
        }

        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    // 단건 일정 조회
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        return new ScheduleResponseDto(schedule);
    }

    // 일정 수정
    public ScheduleResponseDto updateSchedule(
            Long id,
            String title,
            String author,
            String password
    ) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(title, author);

        return new ScheduleResponseDto(schedule);
    }

    // 일정 삭제
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);
    }
}
