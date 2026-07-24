DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    category VARCHAR(255),
    tags JSONB
);