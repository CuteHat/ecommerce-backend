CREATE TABLE couriers
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(64) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

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
    order_id      BIGINT      NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

CREATE TABLE deliveries
(
    id         SERIAL PRIMARY KEY,
    status     VARCHAR(64) NOT NULL,
    courier_id BIGINT      NOT NULL,
    order_id   BIGINT      NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (courier_id) REFERENCES couriers (id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);