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

    @PostMapping(value = "/to-markdown")
    public String convertJsonToMarkdown(@RequestBody Map<String,String> input) {
        try {
            String jsonString = input.get("json");
            if (jsonString == null || jsonString.isEmpty()) {
                return "Missing 'json' field in request.";
            }

            // 将字符串解析为 Map
            Map<String, Object> data = objectMapper.readValue(jsonString, Map.class);

            // 构建 Markdown 表格
            StringBuilder markdown = new StringBuilder();
            markdown.append("| Key | Value |\n");
            markdown.append("|-----|-------|\n");

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                markdown.append("| ")
                        .append(entry.getKey())
                        .append(" | ")
                        .append(entry.getValue())
                        .append(" |\n");
            }

            return markdown.toString();

        } catch (Exception e) {
            return "Failed to parse input JSON: " + e.getMessage();
        }
    }
}
