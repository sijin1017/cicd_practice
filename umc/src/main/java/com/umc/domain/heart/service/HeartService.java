package com.umc.domain.heart.service;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.entity.Board;
import com.umc.domain.heart.entity.Heart;
import com.umc.domain.board.repository.BoardRepository;
import com.umc.domain.heart.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public ApiResponse<Void> heartBoard(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        heartRepository.findByBoardIdAndUserId(boardId, userId)
                .ifPresentOrElse(
                        heart -> { throw new IllegalStateException("Already hearted"); },
                        () -> {
                            Heart heart = new Heart();
                            heart.setBoard(board);
                            heart.setUserId(userId);
                            heartRepository.save(heart);
                        });
        return ApiResponse.onSuccess(null);
    }

    @Transactional
    public ApiResponse<Void> unheartBoard(Long boardId, Long userId) {
        Heart heart = heartRepository.findByBoardIdAndUserId(boardId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Heart not found"));
        heartRepository.delete(heart);
        return ApiResponse.onSuccess(null);
    }

    @Transactional(readOnly = true)
    public ApiResponse<Integer> getHeartsCount(Long boardId) {
        int count = heartRepository.countByBoardId(boardId);
        return ApiResponse.onSuccess(count);
    }

    @Transactional(readOnly = true)
    public ApiResponse<Boolean> hasUserHearted(Long boardId, Long userId) {
        boolean hearted = heartRepository.findByBoardIdAndUserId(boardId, userId).isPresent();
        return ApiResponse.onSuccess(hearted);
    }
}
