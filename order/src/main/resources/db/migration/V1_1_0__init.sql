CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    total_price DECIMAL NOT NULL,
    customer_id BIGINT  NOT NULL,
    created_at  TIMESTAMP DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW()
);

CREATE TABLE order_items
(
    id            SERIAL PRIMARY KEY,
    product_id    BIGINT      NOT NULL,
    product_name  VARCHAR(64) NOT NULL,
    product_price DECIMAL     NOT NULL,
    quantity      INTEGER     NOT NULL,
    seller_id     BIGINT      NOT NULL,
    order_id      BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);