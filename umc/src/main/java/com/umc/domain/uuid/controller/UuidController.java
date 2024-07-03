package com.umc.domain.uuid.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.uuid.service.UuidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/uuids")
@RequiredArgsConstructor
public class UuidController {

    private final UuidService uuidService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> uploadImage(@RequestParam("file") MultipartFile file) {
        ApiResponse<String> response = uuidService.uploadImage(file);
        return ResponseEntity.ok(response);
    }
}
