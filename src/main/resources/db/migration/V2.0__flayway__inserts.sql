INSERT INTO spad_obj_type VALUES(1, 'not_domain_object', now());
INSERT INTO foo VALUES(10, 0, now(), now(), 'A','B', 1);

INSERT INTO role VALUES(1, 'ROLE_ADMIN');
INSERT INTO role VALUES(2, 'ROLE_USER');

INSERT INTO spad_user VALUES (1110, 0, now(), now(), 'admin@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', null, null, 'admin');
INSERT INTO spad_user VALUES (1111, 0, now(),  now(), 'user@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', 'Adam', 'Adamowicz', 'user');
INSERT INTO spad_user VALUES (1112, 0, now(), now(), 'mockUser1@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', 'Jan', null, 'mockUser1');
INSERT INTO spad_user VALUES (1113, 0, now(), now(), 'mockUser2@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', 'John', null, 'mockUser2');

INSERT INTO user_role VALUES(1110, 1);
INSERT INTO user_role VALUES(1111, 2);
INSERT INTO user_role VALUES(1112, 2);
INSERT INTO user_role VALUES(1113, 2);

INSERT INTO post VALUES (110, now(), now(), 0, 'Wczoraj Dagmarka kupiła shaker.', 'Realcja z zakupów', 1112);
INSERT INTO post VALUES (111, now(), now(), 0, 'Wczoraj Dagmarka kupiła kolke.', 'Realcja z zakupów', 1112);
INSERT INTO post VALUES (112, now(), now(), 0, 'Wczoraj Dagmarka nie kupiła butół DKNY :<.', 'Realcja z zakupów', 1112);
