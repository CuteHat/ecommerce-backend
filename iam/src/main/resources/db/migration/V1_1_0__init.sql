CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(64) NOT NULL UNIQUE,
    name       VARCHAR(64) NOT NULL,
    phone      VARCHAR(20) NOT NULL,
    address    TEXT        NOT NULL,
    password   VARCHAR(64) NOT NULL,
    active     BOOLEAN     NOT NULL,
    created_at TIMESTAMP   NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP   NOT NULL DEFAULT NOW()
);

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);