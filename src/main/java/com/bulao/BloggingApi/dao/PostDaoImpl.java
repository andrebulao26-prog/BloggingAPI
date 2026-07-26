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
    public String newPost(String title, String content, String category, String Tags, String createdAt, String updatedAt) {
        return jdbcTemplate.queryForObject("INSERT INTO posts (title, content, category, tags, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?) RETURNING id",String.class,title,content,category,Tags,createdAt, updatedAt);
    }

    @Override
    public void deletePost(Integer id) {
        jdbcTemplate.update("DELETE FROM posts WHERE id="+id);
    }

    @Override
    public String updatePost(Integer id,String title , String content, String category, String Tags, String updatedAt) {

        String query = "UPDATE posts\n";

        query = query + "SET title = \'"+title+"\', " + "content = \'"+content+"\', " + "category = \'"+category+"\', " + "tags = \'"+Tags+"\', " + "updatedAt = \'"+updatedAt+"\'\n";

        query = query + "WHERE id ="+id+"\n";
        query = query + "RETURNING createdAt";

        return jdbcTemplate.queryForObject(query,String.class);
    }

    @Override
    public boolean postExists(Integer id) {
        if (jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM posts WHERE id="+id+")",String.class).equals("t")) {
            return true;
        } else {
            return false;
        }
    }

}
