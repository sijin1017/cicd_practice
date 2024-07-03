package com.umc.domain.post.service;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.repository.BoardRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import com.umc.domain.post.dto.PostRequestDto;
import com.umc.domain.post.dto.PostResponseDto;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public ApiResponse<Void> createPost(PostRequestDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .member(member)
                .board(board)
                .build();

        postRepository.save(post);
        return ApiResponse.onSuccess(null);
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> postList = postRepository.findAll().stream()
                .map(post -> PostResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author(post.getMember().getNickname())
                        .createdAt(post.getCreatedAt())
                        .updatedAt(post.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
        return ApiResponse.onSuccess(postList);
    }

    @Transactional(readOnly = true)
    public ApiResponse<PostResponseDto> getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        PostResponseDto response = PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getMember().getNickname())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();

        return ApiResponse.onSuccess(response);
    }

    @Transactional
    public ApiResponse<Void> updatePost(Long id, PostRequestDto request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);
        return ApiResponse.onSuccess(null);
    }

    @Transactional
    public ApiResponse<Void> deletePost(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        postRepository.deleteById(id);
        return ApiResponse.onSuccess(null);
    }
}
