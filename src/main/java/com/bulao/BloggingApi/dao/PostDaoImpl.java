package com.bulao.BloggingApi.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class PostDaoImpl implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    public PostDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String newPost(String title, String content, String category, String Tags, String createdAt) {
        return jdbcTemplate.queryForObject("INSERT INTO posts (title, content, category, tags, createdAt) VALUES (?, ?, ?, ?, ?) RETURNING id",String.class,title,content,category,Tags,createdAt);

    }

}
