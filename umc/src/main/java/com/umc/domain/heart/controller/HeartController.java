package com.umc.domain.heart.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.heart.service.HeartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class HeartController {

    private final HeartService heartService;

    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }

    @PostMapping("/{boardId}/heart")
    public ResponseEntity<ApiResponse<Void>> heartBoard(@PathVariable Long boardId, @RequestParam Long userId) {
        ApiResponse<Void> response = heartService.heartBoard(boardId, userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{boardId}/unheart")
    public ResponseEntity<ApiResponse<Void>> unheartBoard(@PathVariable Long boardId, @RequestParam Long userId) {
        ApiResponse<Void> response = heartService.unheartBoard(boardId, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{boardId}/hearts")
    public ResponseEntity<ApiResponse<Integer>> getHeartsCount(@PathVariable Long boardId) {
        ApiResponse<Integer> response = heartService.getHeartsCount(boardId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{boardId}/hearted")
    public ResponseEntity<ApiResponse<Boolean>> hasUserHearted(@PathVariable Long boardId, @RequestParam Long userId) {
        ApiResponse<Boolean> response = heartService.hasUserHearted(boardId, userId);
        return ResponseEntity.ok(response);
    }
}
