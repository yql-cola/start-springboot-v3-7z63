package com.example.webframework.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MarkdownController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/to-markdown", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String convertJsonToMarkdown(@RequestBody String inputJson) {
        StringBuilder markdown = new StringBuilder();

        try {
            Map<String, Object> map = objectMapper.readValue(inputJson, new TypeReference<>() {});

            // 表头
            markdown.append("| Key | Value |\n");
            markdown.append("|-----|-------|\n");

            // 遍历 JSON 构建表格
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                markdown.append("| ")
                        .append(entry.getKey())
                        .append(" | ")
                        .append(entry.getValue())
                        .append(" |\n");
            }

        } catch (Exception e) {
            return "解析 JSON 失败: " + e.getMessage();
        }

        return markdown.toString();
    }
}
