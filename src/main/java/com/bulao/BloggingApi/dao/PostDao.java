package com.bulao.BloggingApi.dao;

import org.springframework.stereotype.Service;

@Service
public interface PostDao {

    String newPost(String title, String content, String category, String Tags, String createdAt);

}
