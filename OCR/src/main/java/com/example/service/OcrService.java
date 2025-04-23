package com.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class OcrService {

    @Value("${naver.ocr.client-url}")
    private String clientUrl;

    @Value("${naver.ocr.client-secret-key}")
    private String clientSecretKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String processImage(MultipartFile file) throws IOException {
        // 메시지 객체 생성
        Map<String, Object> message = new HashMap<>();
        message.put("version", "V2");
        message.put("requestId", UUID.randomUUID().toString());
        message.put("timestamp", System.currentTimeMillis());

        // 이미지 정보 생성
        Map<String, String> imageInfo = new HashMap<>();
        imageInfo.put("format", getFileExtension(file.getOriginalFilename()));
        imageInfo.put("name", file.getOriginalFilename());
        message.put("images", Collections.singletonList(imageInfo));

        // multipart/form-data 요청 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("X-OCR-SECRET", clientSecretKey);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("message", objectMapper.writeValueAsString(message));
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        // API 호출
        ResponseEntity<String> response = restTemplate.postForEntity(clientUrl, request, String.class);
        return response.getBody();
    }

    private String getFileExtension(String filename) {
        if (filename == null) {
            return "jpg"; // 기본값
        }
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "jpg";
            case "png":
                return "png";
            case "pdf":
                return "pdf";
            case "tif":
            case "tiff":
                return "tif";
            default:
                return "jpg"; // 기본값
        }
    }
} 