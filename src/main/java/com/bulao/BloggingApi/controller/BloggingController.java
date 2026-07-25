package com.bulao.BloggingApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class BloggingController {

    @Autowired
    private ObjectMapper objectMapper;

    String[] RequiredKeys = {"title","content","category","tags"};

    private final JdbcTemplate jdbcTemplate;

    public BloggingController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public Map<String, Object> createPost(@RequestBody String body) {

        Map<String, Object> status = new HashMap<>();
        status.put("Status", "400 Bad Request");

        JsonNode rootNode = objectMapper.readTree(body);

        if (rootNode.isEmpty()) {
            return status;
        }
        for (String keyName : RequiredKeys) {
            if (rootNode.findValue(keyName) == null) {
                return status;
            }
        }

        String title = rootNode.path("title").asString();
        String content = rootNode.path("content").asString();
        String category = rootNode.path("category").asString();
        JsonNode tagsPath = rootNode.path("tags");

        String Tags = "{";

        for (JsonNode tag : tagsPath) {
            Tags = Tags + "\"" + tag.asString() + "\",";
        }
        Tags = Tags.substring(0,Tags.length()-1) + "}";

        System.out.println(Tags);

        jdbcTemplate.update("INSERT INTO posts (title, content, category, tags) VALUES (?, ?, ?, ?)",
                title, content, category, Tags
        );

        status.replace("Status", "201 Created");

        return status;

    }

    @PutMapping("/{id}")
    public Map<String, Object> updatePost(@PathVariable Integer id) {
        Map<String, Object> status = new HashMap<>();
        return status;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deletePost(@PathVariable Integer id) {
        Map<String, Object> status = new HashMap<>();
        return status;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPost(
            @PathVariable Integer id,
            @RequestParam(required = false) String term) {

        Map<String, Object> status = new HashMap<>();
        return status;

    }

}
