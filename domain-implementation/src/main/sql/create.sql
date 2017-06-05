CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    username varchar(255) NOT NULL,
    UNIQUE(username)
);

CREATE TABLE posts(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL REFERENCES users(id),
    content TEXT NOT NULL
);

CREATE TABLE comments(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL REFERENCES users(id),
    post_id BIGSERIAL REFERENCES posts(id),
    content varchar(1000) NOT NULL
);