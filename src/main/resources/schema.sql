DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    content VARCHAR(255),
    category VARCHAR(255),
    tags VARCHAR(255),
    createdAt VARCHAR(255),
    updatedAt VARCHAR(255)
);