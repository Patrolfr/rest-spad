create table foo (id  bigserial not null, email varchar(255), name varchar(255), primary key (id));
create table spad_obj_type (id int8 not null, name varchar(255), primary key (id));

INSERT INTO foo VALUES(10, 'A','B');