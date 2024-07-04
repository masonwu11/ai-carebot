CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);

ALTER TABLE users ADD COLUMN IF NOT EXISTS password VARCHAR(255);