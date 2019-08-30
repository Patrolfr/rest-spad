-- DDL
create table spad_obj_type (id int8 not null, type varchar(255), last_modified timestamp, primary key (id));
create table foo (id  bigserial not null, email varchar(255), name varchar(255), object_type_id int8 not null references spad_obj_type, primary key (id));


INSERT INTO spad_obj_type VALUES(1, 'not_domain_object', now());
INSERT INTO foo VALUES(10, 'A','B', 1);