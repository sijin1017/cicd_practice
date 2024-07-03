package com.umc.domain.comment.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.comment.dto.CommentRequestDto;
import com.umc.domain.comment.dto.CommentResponseDto;
import com.umc.domain.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createComment(@RequestBody CommentRequestDto request) {
        ApiResponse<Void> response = commentService.createComment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getAllComments() {
        ApiResponse<List<CommentResponseDto>> response = commentService.getAllComments();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentResponseDto>> getCommentById(@PathVariable Long id) {
        ApiResponse<CommentResponseDto> response = commentService.getCommentById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto request) {
        ApiResponse<Void> response = commentService.updateComment(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long id) {
        ApiResponse<Void> response = commentService.deleteComment(id);
        return ResponseEntity.ok(response);
    }
}
