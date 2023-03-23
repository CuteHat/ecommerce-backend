CREATE TABLE notifications
(
    id          SERIAL PRIMARY KEY,
    message     TEXT        NOT NULL,
    type        VARCHAR(50) NOT NULL,
    destination TEXT        NOT NULL,
    is_read     BOOLEAN     NOT NULL DEFAULT false,
    user_id     BIGINT      NOT NULL,
    created_at  TIMESTAMP            DEFAULT NOW(),
    updated_at  TIMESTAMP            DEFAULT NOW()
);
