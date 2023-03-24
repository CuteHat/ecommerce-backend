CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE products
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    seller_id    BIGINT         NOT NULL,
    seller_email VARCHAR(64)    NOT NULL,
    stock        INTEGER        NOT NULL,
    category_id  BIGINT         NOT NULL,
    created_at   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);