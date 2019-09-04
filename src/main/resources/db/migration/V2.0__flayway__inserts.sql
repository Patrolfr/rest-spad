INSERT INTO spad_obj_type VALUES(1, 'not_domain_object', now());
INSERT INTO foo VALUES(10, 'A','B', 1);

INSERT INTO role VALUES(1, 'ROLE_ADMIN');
INSERT INTO role VALUES(2, 'ROLE_USER');

INSERT INTO spad_user VALUES (1110, now(), 'admin@test.test', '$2y$10$GX1XFjN0T8hBumQzEjuGaeA6c2VYVp6skkuNPX59k9zdQFjvMQpA6', null, null, 'admin');
INSERT INTO spad_user VALUES (1111, now(), 'user@test.test', '$2y$10$GX1XFjN0T8hBumQzEjuGaeA6c2VYVp6skkuNPX59k9zdQFjvMQpA6', 'Adam', 'Adamowicz', 'user');
INSERT INTO spad_user VALUES (1112, now(), 'mockUser1@test.test', '$2y$10$GX1XFjN0T8hBumQzEjuGaeA6c2VYVp6skkuNPX59k9zdQFjvMQpA6', 'Jan', null, 'mockUser1');
INSERT INTO spad_user VALUES (1113, now(), 'mockUser2@test.test', '$2y$10$GX1XFjN0T8hBumQzEjuGaeA6c2VYVp6skkuNPX59k9zdQFjvMQpA6', 'John', null, 'mockUser2');

INSERT INTO user_role VALUES(1110, 1);
INSERT INTO user_role VALUES(1111, 2);
INSERT INTO user_role VALUES(1112, 2);
INSERT INTO user_role VALUES(1113, 2);