package com.umc.domain.board.controller;

import com.umc.common.aws.s3.AmazonS3Manager;
import com.umc.common.response.ApiResponse;
import com.umc.domain.board.dto.BoardRequestDto;
import com.umc.domain.board.dto.BoardResponseDto;
import com.umc.domain.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final AmazonS3Manager amazonS3Manager;


    public BoardController(BoardService boardService, AmazonS3Manager amazonS3Manager) {
        this.boardService = boardService;
        this.amazonS3Manager = amazonS3Manager;
    }

    @PostMapping
    public ApiResponse<Void> createBoard(
            @RequestParam("file") MultipartFile file) {

        String imageUrl = amazonS3Manager.uploadFile(UUID.randomUUID().toString(), file);
        boardService.createBoard(imageUrl);
        return ApiResponse.onSuccess(null);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BoardResponseDto>>> getAllBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<BoardResponseDto>> response = boardService.getAllBoards(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BoardResponseDto>> getBoardById(@PathVariable Long id) {
        ApiResponse<BoardResponseDto> response = boardService.getBoardById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto request) {
        ApiResponse<Void> response = boardService.updateBoard(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBoard(@PathVariable Long id) {
        ApiResponse<Void> response = boardService.deleteBoard(id);
        return ResponseEntity.ok(response);
    }
}
