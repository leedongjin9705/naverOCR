package com.example.controller;

import com.example.service.OcrService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class OcrController {

    private final OcrService ocrService;
    private final ObjectMapper objectMapper;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            String result = ocrService.processImage(file);
            JsonNode jsonResult = objectMapper.readTree(result);

            // OCR 결과 처리
            if (jsonResult.has("images") && jsonResult.get("images").isArray() && jsonResult.get("images").size() > 0) {
                JsonNode image = jsonResult.get("images").get(0);
                if ("SUCCESS".equals(image.get("inferResult").asText())) {
                    JsonNode nameCard = image.get("nameCard").get("result");
                    model.addAttribute("success", true);
                    model.addAttribute("name", getTextFromNode(nameCard, "name"));
                    model.addAttribute("company", getTextFromNode(nameCard, "company"));
                    model.addAttribute("position", getTextFromNode(nameCard, "position"));
                    model.addAttribute("mobile", getTextFromNode(nameCard, "mobile"));
                    model.addAttribute("tel", getTextFromNode(nameCard, "tel"));
                    model.addAttribute("fax", getTextFromNode(nameCard, "fax"));
                    model.addAttribute("email", getTextFromNode(nameCard, "email"));
                    model.addAttribute("address", getTextFromNode(nameCard, "address"));
                } else {
                    model.addAttribute("success", false);
                    model.addAttribute("error", image.get("message").asText());
                }
            } else {
                model.addAttribute("success", false);
                model.addAttribute("error", "OCR 처리 결과가 없습니다.");
            }
        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("error", "이미지 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "result";
    }

    private String getTextFromNode(JsonNode node, String field) {
        if (node.has(field) && node.get(field).isArray() && node.get(field).size() > 0) {
            return node.get(field).get(0).get("text").asText();
        }
        return "";
    }
} 