INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'), ('ROLE_USER'), ('ROLE_MODERATOR')
ON CONFLICT (name) DO NOTHING;

INSERT INTO users (username, password, email)
VALUES
    ('ferrington' ,'$2a$10$PcSvcvAMh0UjiS8CsiNbzulmxR4ua0g3PDg.eNQGTfwXPOQdUIMZC', 'ferrington@gmail.com')
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_roles (user_id, role_id)
VALUES
    ((SELECT id FROM users WHERE username = 'ferrington'), (SELECT id FROM roles WHERE name = 'ROLE_USER'))
ON CONFLICT (user_id, role_id) DO NOTHING;

INSERT INTO channels(name, description)
VALUES
    ('General', 'A general place to chat'),
    ('Games', 'Talk about games'),
    ('Movies', 'Talk about movies'),
    ('Pets', 'Talk about pets')