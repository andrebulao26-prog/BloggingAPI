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
    private String[] tagss;

}
