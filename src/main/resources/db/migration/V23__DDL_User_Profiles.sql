create table user_profile (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  about_me TEXT NOT NULL,
  twitch_stream varchar(255) DEFAULT NULL,
  twitter_name varchar(100) DEFAULT NULL,
  user_id bigint(20) NOT NULL,
  primary key(id)
);

alter table user_profile
  ADD CONSTRAINT FK_UP_user_id foreign key (user_id) references scrappie_user (id);