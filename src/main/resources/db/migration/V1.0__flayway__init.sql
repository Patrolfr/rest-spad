-- DDL
create table spad_obj_type (id int8 not null, type varchar(255), last_modified timestamp, primary key (id));
create table foo (id  bigserial not null, version int4 not null, created_date timestamp, last_modified_date timestamp,  email varchar(255), name varchar(255), object_type_id int8 not null references spad_obj_type, primary key (id));

create table spad_user (id  bigserial not null, version int4 not null,  created_date timestamp, last_modified_date timestamp, email varchar(255) not null, encrypted_password varchar(255) not null, first_name varchar(255), last_name varchar(255), login varchar(255) not null, primary key (id));
create table role (id  bigserial not null, name varchar(255), primary key (id));
create table user_role (user_id int8 not null references spad_user, role_id int8 not null references role, primary key (user_id, role_id));

create table post (id  bigserial not null, created_date timestamp, last_modified_date timestamp, version int4 not null, text varchar(255), title varchar(255), poster_id int8 not null references spad_user, primary key (id));