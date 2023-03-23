-- Insert users
INSERT INTO users (email, name, phone, address, password, active)
VALUES ('admin@example.com', 'Admin', '(123) 456-7890', '123 Main St, Anytown USA',
        '$2a$10$FNfvShQQBRdjpJdUDHjiG.mZa3or3YagVloN13rl2rK418KaIp4X.', true),
       ('seller@example.com', 'Seller', '(555) 555-5555', '456 Maple Ave, Anytown USA',
        '$2a$10$FNfvShQQBRdjpJdUDHjiG.mZa3or3YagVloN13rl2rK418KaIp4X.', true),
       ('client@example.com', 'Client', '(111) 111-1111', '789 Oak St, Anytown USA',
        '$2a$10$FNfvShQQBRdjpJdUDHjiG.mZa3or3YagVloN13rl2rK418KaIp4X.', true);

-- Assign roles to users
INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON
        (u.email = 'admin@example.com' AND r.name = 'ADMINISTRATOR') OR
        (u.email = 'seller@example.com' AND r.name = 'SELLER') OR
        (u.email = 'client@example.com' AND r.name = 'CLIENT');
