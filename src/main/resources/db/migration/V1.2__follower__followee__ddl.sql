
create table follower_followee
(
  follower_id bigint not null,
  followee_id bigint not null,
  primary key (follower_id, followee_id)
);

alter table follower_followee add constraint followee_fkey_constraint_spad_user_0 foreign key (follower_id) references spad_user;
alter table follower_followee add constraint follower_fkey_constraint_spad_user_1 foreign key (followee_id) references spad_user;
