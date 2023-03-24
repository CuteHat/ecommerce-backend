CREATE TABLE notifications
(
    id         SERIAL PRIMARY KEY,
    message    TEXT        NOT NULL,
    type       VARCHAR(50) NOT NULL,
    recipient  TEXT        NOT NULL,
    created_at TIMESTAMP            DEFAULT NOW(),
    updated_at TIMESTAMP            DEFAULT NOW()
);
