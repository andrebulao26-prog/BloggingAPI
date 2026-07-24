package com.bulao.BloggingApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Map<String, Object> createPost(@RequestBody String body) {

        Map<String, Object> status = new HashMap<>();

        JsonNode rootNode = objectMapper.readTree(body);
        String title = rootNode.path("title").asString();

        System.out.println("Recieved post request " + title);

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
