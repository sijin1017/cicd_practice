package com.umc.domain.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardResponseDto {
    private Long id;
    private String name;
    private String description;
}
