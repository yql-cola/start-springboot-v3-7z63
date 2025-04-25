package com.example.webframework.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MarkdownController {

    @PostMapping("/to-markdown")
    public String convertJsonToMarkdown(@RequestBody Map<String, Object> inputJson) {
        StringBuilder markdown = new StringBuilder();

        // 表头
        markdown.append("| Key | Value |\n");
        markdown.append("|-----|-------|\n");

        // 遍历 JSON 构建表格
        for (Map.Entry<String, Object> entry : inputJson.entrySet()) {
            markdown.append("| ")
                    .append(entry.getKey())
                    .append(" | ")
                    .append(entry.getValue())
                    .append(" |\n");
        }

        return markdown.toString();
    }
}
