INSERT INTO spad_obj_type VALUES(1, 'not_domain_object', now());
INSERT INTO foo VALUES(10, 0, now(), now(), 'A','B', 1);

-- ROLES
INSERT INTO role VALUES(1, 'ROLE_ADMIN');
INSERT INTO role VALUES(2, 'ROLE_USER');

-- USERS
INSERT INTO spad_user VALUES (1000, 0, now(), now(), 'admin@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', null, null, 'admin');
INSERT INTO user_role VALUES(1000, 1);
INSERT INTO user_role VALUES(1000, 2);

INSERT INTO spad_user VALUES (1111, 0, now(),  now(), 'test1@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', 'testName1', 'testSFame1', 'test1');
INSERT INTO spad_user VALUES (1112, 0, now(),  now(), 'test2@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', 'testName2', 'testSFame2', 'test2');
INSERT INTO spad_user VALUES (1113, 0, now(),  now(), 'test3@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', 'testName3', 'testSFame3', 'test3');
INSERT INTO spad_user VALUES (1114, 0, now(),  now(), 'test4@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', 'testName4', 'testSFame4', 'test4');
INSERT INTO spad_user VALUES (1115, 0, now(),  now(), 'test5@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', 'testName5', 'testSFame5', 'test5');
INSERT INTO spad_user VALUES (1116, 0, now(),  now(), 'test6@test.test', '$2a$10$zMIBSOThe9yA6VJ/EDQ.v.FS8B1.ZWGTvapVk5/797cV7l5u81Fau', 'testName6', 'testSFame6', 'test6');
INSERT INTO user_role VALUES (1111, 2), (1112, 2), (1113, 2), (1114, 2), (1115, 2), (1116, 2);

-- POSTS
INSERT INTO post VALUES (110, now(), now(), 0, 'Wczoraj Dagmarka kupiła shaker.', 'Realcja z zakupów', 1111);
INSERT INTO post VALUES (111, now(), now(), 0, 'Wczoraj Dagmarka kupiła kolke.', 'Realcja z zakupów', 1111);
INSERT INTO post VALUES (112, now(), now(), 0, 'Wczoraj Dagmarka nie kupiła butÓW DKNY :<.', 'Realcja z zakupów', 1112);
INSERT INTO post VALUES (113, now(), now(), 0, 'Wczoraj.....', 'Realcja z ...', 1113);
