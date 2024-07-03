package com.umc.domain.uuid.service;

import com.umc.common.response.ApiResponse;
import com.umc.common.aws.s3.AmazonS3Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UuidService {

    private final AmazonS3Manager amazonS3Manager;

    @Transactional
    public ApiResponse<String> uploadImage(MultipartFile file) {
        String keyName = UUID.randomUUID().toString();
        String fileUrl = amazonS3Manager.uploadFile(keyName, file);
        return ApiResponse.onSuccess(fileUrl);
    }
}
