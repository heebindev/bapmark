package com.heebin.bapmark.controller;

import com.heebin.bapmark.dto.CreateStampBoardRequest;
import com.heebin.bapmark.entity.StampBoard;
import com.heebin.bapmark.service.StampBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stampboards")
@RequiredArgsConstructor
public class StampBoardController {

    private final StampBoardService stampBoardService;

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody CreateStampBoardRequest request) {
        StampBoard stampBoard;
        stampBoard = stampBoardService.createStampBoard(
                request.getEmail(),
                request.getTitle(),
                request.getDescription(),
                request.getBackgroundColor()
        );
        return ResponseEntity.ok(stampBoard);
    }
}
