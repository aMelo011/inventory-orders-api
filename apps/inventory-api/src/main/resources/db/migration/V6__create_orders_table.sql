CREATE TABLE orders(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT REFERENCES app_user(id),
    status VARCHAR(20),
    created_at TIMESTAMP
);