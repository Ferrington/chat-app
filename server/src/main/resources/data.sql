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
    ('Pets', 'Talk about pets');

INSERT INTO messages (channel_id, user_id, content, created, updated)
VALUES
    (1, 1, 'This is a dummy message 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (1, 1, 'This is a dummy message 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (1, 1, 'This is a dummy message 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (1, 1, 'This is a dummy message 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (1, 1, 'This is a dummy message 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (1, 1, 'This is a dummy message 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (1, 1, 'This is a dummy message 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (1, 1, 'This is a dummy message 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (1, 1, 'This is a dummy message 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 20', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
     (1, 1, 'This is a dummy message 21', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 1, 'This is a dummy message 22', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
