package com.bulao.BloggingApi.services;

import com.bulao.BloggingApi.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class PostService {

    @Autowired
    private ObjectMapper objectMapper;

    private final PostDao postDao;

    public PostService(ObjectMapper objectMapper, PostDao postDao) {
        this.postDao = postDao;
    }

    public ResponseEntity<Map<String, Object>> createPost(String body) {

        String[] RequiredKeys = {"title","content","category","tags"};
        Map<String, Object> returnJson = new HashMap<>();

        JsonNode rootNode = objectMapper.readTree(body);

        if (rootNode.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnJson);
        }
        for (String keyName : RequiredKeys) {
            if (rootNode.findValue(keyName) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnJson);
            }
        }

        String title = rootNode.path("title").asString();
        String content = rootNode.path("content").asString();
        String category = rootNode.path("category").asString();
        JsonNode tagsPath = rootNode.path("tags");

        String createdAt = String.valueOf(LocalDateTime.now());

        String Tags = "[";

        for (JsonNode tag : tagsPath) {
            Tags = Tags + '"' + tag.asString() + '"' + ",";
        }
        Tags = Tags.substring(0,Tags.length()-1) + "]";

        returnJson.put("title", title);
        returnJson.put("content",content);
        returnJson.put("category",category);
        returnJson.put("tags",Tags);
        returnJson.put("createdAt",createdAt);
        returnJson.put("updatedAt",createdAt);

        String newID = postDao.newPost(title,content,category,Tags,createdAt,createdAt);

        returnJson.put("id",newID);

        return ResponseEntity.status(HttpStatus.OK).body(returnJson);

    }

    public ResponseEntity<Map<String, Object>> updatePost(Integer id, String body) {

        if (postDao.postExists(id) == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<>());
        }

        String[] RequiredKeys = {"title","content","category","tags"};
        Map<String, Object> returnJson = new HashMap<>();

        JsonNode rootNode = objectMapper.readTree(body);

        if (rootNode.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnJson);
        }
        for (String keyName : RequiredKeys) {
            if (rootNode.findValue(keyName) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnJson);
            }
        }

        String title = rootNode.path("title").asString();
        String content = rootNode.path("content").asString();
        String category = rootNode.path("category").asString();
        JsonNode tagsPath = rootNode.path("tags");

        String updatedAt = String.valueOf(LocalDateTime.now());

        String Tags = "[";

        for (JsonNode tag : tagsPath) {
            Tags = Tags + '"' + tag.asString() + '"' + ",";
        }
        Tags = Tags.substring(0,Tags.length()-1) + "]";

        returnJson.put("id",id);
        returnJson.put("title", title);
        returnJson.put("content",content);
        returnJson.put("category",category);
        returnJson.put("tags",Tags);
        returnJson.put("updatedAt",updatedAt);

        String createdAt = postDao.updatePost(id,title,content,category,Tags,updatedAt);

        returnJson.put("createdAt",createdAt);

        return ResponseEntity.status(HttpStatus.OK).body(returnJson);

    }

    public ResponseEntity<Map<String, Object>> deletePost(Integer id) {
        if (postDao.postExists(id) == true) {
            postDao.deletePost(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new HashMap<>());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<>());
        }
    }



}
