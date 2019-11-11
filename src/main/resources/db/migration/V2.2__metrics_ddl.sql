 create table spad_metric (
   id  bigserial not null,
   created_date timestamp,
   last_modified_date timestamp,
   version int4 not null,
   chunk_sequence int4,
   content varchar(255),
   session_user_login varchar(255),
   uuid char(36),
   primary key (id))
