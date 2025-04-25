package com.example.webframework.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/convert")
public class XmlToJsonController {

    private final XmlMapper xmlMapper;
    private final ObjectMapper jsonMapper;

    public XmlToJsonController() {
        this.xmlMapper = new XmlMapper();
        this.jsonMapper = new ObjectMapper();
    }

    @PostMapping(value = "/xml-to-json", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> convertXmlToJson(@RequestBody String xml) {
        try {
            // 将 XML 反序列化为 JsonNode
            JsonNode node = xmlMapper.readTree(xml.getBytes(StandardCharsets.UTF_8));

            // 将 JsonNode 序列化为 JSON 字符串
            String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);

            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Invalid XML format: " + e.getMessage() + "\"}");
        }
    }
}

