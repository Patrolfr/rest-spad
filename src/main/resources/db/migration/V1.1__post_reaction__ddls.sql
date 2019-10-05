-- DDL
create table reaction
(
  id                  bigserial not null constraint reaction_pkey primary key,
  created_date        timestamp,
  last_modified_date  timestamp,
  version             integer   not null,
  author_id           bigint constraint reaction_constraint_spad_user_0 references spad_user,
  post_id             bigint constraint reaction_constraint_post_0 references post,
  object_type_id      bigint not null constraint reaction_constraint_spad_object_type_0 references spad_obj_type,
  content            text,
  title              varchar(255)
);