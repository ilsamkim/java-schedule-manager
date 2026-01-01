package com.jap.controller;

import com.jap.dto.ScheduleCreateRequestDto;
import com.jap.dto.ScheduleDeleteRequestDto;
import com.jap.dto.ScheduleResponseDto;
import com.jap.dto.ScheduleUpdateRequestDto;
import com.jap.entity.Schedule;
import com.jap.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @RequestBody ScheduleCreateRequestDto requestDto
    ) {
        Schedule schedule = scheduleService.createSchedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getAuthor(),
                requestDto.getPassword()
        );

        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(
            @RequestParam(required = false) String author
    ) {
        return ResponseEntity.ok(scheduleService.getSchedules(author));
    }

    // 선택 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(
                id,
                requestDto.getTitle(),
                requestDto.getAuthor(),
                requestDto.getPassword()
        );

        return ResponseEntity.ok(responseDto);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleDeleteRequestDto requestDto
    ) {
        scheduleService.deleteSchedule(id, requestDto.getPassword());
        return ResponseEntity.noContent().build();
    }
}
