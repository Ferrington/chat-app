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

-- This needs evaluation. not working correctly. 'content' is considered as a long value?
--INSERT INTO messages (channel_id, user_id, content, created, updated)
--VALUES
--    (1, 1, 'This is a dummy message 1', CURRENT_TIMESTAMP - INTERVAL '22' MINUTE, CURRENT_TIMESTAMP- INTERVAL '22' MINUTE),
--    (1, 1, 'This is a dummy message 2', CURRENT_TIMESTAMP- - INTERVAL '21' MINUTE, CURRENT_TIMESTAMP- INTERVAL '21' MINUTE),
--     (1, 1, 'This is a dummy message 3', CURRENT_TIMESTAMP - INTERVAL '20' MINUTE, CURRENT_TIMESTAMP - INTERVAL '20' MINUTE),
--    (1, 1, 'This is a dummy message 4', CURRENT_TIMESTAMP - INTERVAL '19' MINUTE, CURRENT_TIMESTAMP - INTERVAL '19' MINUTE),
--     (1, 1, 'This is a dummy message 5', CURRENT_TIMESTAMP - INTERVAL '18' MINUTE, CURRENT_TIMESTAMP - INTERVAL '18' MINUTE),
--    (1, 1, 'This is a dummy message 6', CURRENT_TIMESTAMP - INTERVAL '17' MINUTE, CURRENT_TIMESTAMP - INTERVAL '17' MINUTE),
--     (1, 1, 'This is a dummy message 7', CURRENT_TIMESTAMP - INTERVAL '16' MINUTE, CURRENT_TIMESTAMP - INTERVAL '16' MINUTE),
--    (1, 1, 'This is a dummy message 8', CURRENT_TIMESTAMP - INTERVAL '15' MINUTE, CURRENT_TIMESTAMP - INTERVAL '15' MINUTE),
--     (1, 1, 'This is a dummy message 9', CURRENT_TIMESTAMP - INTERVAL '14' MINUTE, CURRENT_TIMESTAMP - INTERVAL '14' MINUTE),
--    (1, 1, 'This is a dummy message 10', CURRENT_TIMESTAMP - INTERVAL '13' MINUTE, CURRENT_TIMESTAMP - INTERVAL '13' MINUTE),
--     (1, 1, 'This is a dummy message 11', CURRENT_TIMESTAMP - INTERVAL '12' MINUTE, CURRENT_TIMESTAMP - INTERVAL '12' MINUTE),
--    (1, 1, 'This is a dummy message 12', CURRENT_TIMESTAMP - INTERVAL '11' MINUTE, CURRENT_TIMESTAMP - INTERVAL '11' MINUTE),
--     (1, 1, 'This is a dummy message 13', CURRENT_TIMESTAMP - INTERVAL '10' MINUTE, CURRENT_TIMESTAMP - INTERVAL '10' MINUTE),
--    (1, 1, 'This is a dummy message 14', CURRENT_TIMESTAMP - INTERVAL '9' MINUTE, CURRENT_TIMESTAMP - INTERVAL '9' MINUTE),
--     (1, 1, 'This is a dummy message 15', CURRENT_TIMESTAMP - INTERVAL '8' MINUTE, CURRENT_TIMESTAMP - INTERVAL '8' MINUTE),
--    (1, 1, 'This is a dummy message 16', CURRENT_TIMESTAMP - INTERVAL '7' MINUTE, CURRENT_TIMESTAMP - INTERVAL '7' MINUTE),
--     (1, 1, 'This is a dummy message 17', CURRENT_TIMESTAMP - INTERVAL '6' MINUTE, CURRENT_TIMESTAMP - INTERVAL '6' MINUTE),
--    (1, 1, 'This is a dummy message 18', CURRENT_TIMESTAMP - INTERVAL '5' MINUTE, CURRENT_TIMESTAMP - INTERVAL '5' MINUTE),
--    (1, 1, 'This is a dummy message 19', CURRENT_TIMESTAMP - INTERVAL '4' MINUTE, CURRENT_TIMESTAMP - INTERVAL '4' MINUTE),
--    (1, 1, 'This is a dummy message 20', CURRENT_TIMESTAMP - INTERVAL '3' MINUTE, CURRENT_TIMESTAMP - INTERVAL '3' MINUTE),
--     (1, 1, 'This is a dummy message 21', CURRENT_TIMESTAMP - INTERVAL '2' MINUTE, CURRENT_TIMESTAMP - INTERVAL '2' MINUTE),
--    (1, 1, 'This is a dummy message 22', CURRENT_TIMESTAMP - INTERVAL '1' MINUTE, CURRENT_TIMESTAMP - INTERVAL '1' MINUTE);
