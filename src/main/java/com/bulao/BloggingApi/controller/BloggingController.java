package com.bulao.BloggingApi.controller;

import com.bulao.BloggingApi.dao.PostDao;
import com.bulao.BloggingApi.domain.Post;
import com.bulao.BloggingApi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class BloggingController {

    private final PostService postService;

    public BloggingController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody String body) {
        return postService.createPost(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePost(@PathVariable Integer id, @RequestBody String body) {
        return postService.updatePost(id,body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Integer id) {
        return postService.deletePost(id);
    }

    @GetMapping()
    public ResponseEntity<List<Map<String, Object>>> getPost(@RequestParam(required = false) String term) {

        if (term==null) {
            term = "";
        }

        return postService.getPost(term);

    }

}
