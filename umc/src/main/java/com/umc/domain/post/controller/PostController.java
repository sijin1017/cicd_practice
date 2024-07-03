package com.umc.domain.post.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.post.dto.PostRequestDto;
import com.umc.domain.post.dto.PostResponseDto;
import com.umc.domain.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestBody PostRequestDto request) {
        ApiResponse<Void> response = postService.createPost(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getAllPosts() {
        ApiResponse<List<PostResponseDto>> response = postService.getAllPosts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<ApiResponse<PostResponseDto>> getPostById(@PathVariable("post_id") Long postId) {
        ApiResponse<PostResponseDto> response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{post_id}")
    public ResponseEntity<ApiResponse<Void>> updatePost(@PathVariable("post_id") Long postId, @RequestBody PostRequestDto request) {
        ApiResponse<Void> response = postService.updatePost(postId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable("post_id") Long postId) {
        ApiResponse<Void> response = postService.deletePost(postId);
        return ResponseEntity.ok(response);
    }
}
