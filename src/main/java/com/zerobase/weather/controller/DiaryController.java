package com.zerobase.weather.controller;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/create/diary")
    @Operation(summary = "일기 생성",
            description = "일기를 생성할 때 사용하는 API",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "일기 생성 완료"
                    )
            })
    void createDiary(@Parameter(description = "일기의 날짜", example = "2024-01-01")
                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text){
       diaryService.createDiary(date, text);
    }

    @GetMapping("/read/diary")
    @Operation(summary = "일기 불러오기",
            description = "해당 날짜에 맞는 일기를 불러오는 API",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "일기 불러오기 완료"
                    )
            })
    List<Diary> readDiary(@Parameter(description = "일기의 날짜", example = "2024-01-01")
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return diaryService.readDiary(date);
    }

    @GetMapping("/read/diaries")
    @Operation(summary = "여러 일기 불러오기",
            description = "특정 범위의 날짜에 맞는 일기를 불러오는 API",
            responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "일기 여러 개 불러오기 완료"
            )
    })
    List<Diary> readDiaries(@Parameter(description = "시작 날짜", example = "2024-01-01")
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                            @Parameter(description = "종료 날짜", example = "2024-02-01")
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        return diaryService.readDiaries(startDate, endDate);
    }

    @PutMapping("/update/diary")
    @Operation(summary = "일기 업데이트",
            description = "특정 날짜의 첫 번째 일기를 수정하는 API",
            responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "일기 업데이트 완료"
            )
    })
    void updateDiary(@Parameter(description = "일기 날짜", example = "2024-01-01")
                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text){
        diaryService.updateDiary(date, text);
    }

    @DeleteMapping("delete/diary")
    @Operation(summary = "일기 삭제",
            description = "특정 날짜의 첫 번째 일기를 삭제하는 API",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "일기 삭제 완료"
                    )
            })
    void deleteiary(@Parameter(description = "일기 날짜", example = "2024-01-01")
                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        diaryService.deleteDiary(date);
    }
}
