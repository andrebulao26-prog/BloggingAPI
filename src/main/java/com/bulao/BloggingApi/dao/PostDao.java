package com.bulao.BloggingApi.dao;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PostDao {

    String newPost(String title, String content, String category, String Tags, String createdAt, String updatedAt);
    void deletePost(Integer id);
    String updatePost(Integer id,String title, String content, String category, String Tags, String updatedAt);
    List<Map<String, Object>> getPost (String term);
    boolean postExists(Integer id);

}
