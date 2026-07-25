package com.bulao.BloggingApi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

public class Post {

    private String title;
    private String content;
    private String category;
    private String[] tags;

    private final JdbcTemplate jdbcTemplate;

    public Post(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void Create () {

        jdbcTemplate.update("INSERT INTO posts (title, content, category, tags) VALUES (?, ?, ?, ?)",
            this.title, this.content, this.category, this.tags
        );

    }

}
