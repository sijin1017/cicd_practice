package com.umc.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommentRequestDto {
    private String content;
    private Long postId;
    private Long memberId;
}