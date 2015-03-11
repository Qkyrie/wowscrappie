create table role (
  id int(11) NOT NULL,
  authority varchar(25) NOT NULL,
  primary key(id)
);

create table scrappie_user(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(100) DEFAULT NULL,
  facebook_id varchar(100) DEFAULT NULL,
  twitter_id varchar(100) DEFAULT NULL,
  access_token varchar(100) DEFAULT NULL,
  refresh_token varchar(100) DEFAULT NULL,
  account_non_expired bool DEFAULT TRUE,
  account_non_locked bool DEFAULT TRUE,
  credentials_non_expired bool DEFAULT TRUE,
  enabled bool DEFAULT TRUE,
  email varchar(100) NOT NULL,
  activationcode VARCHAR(40) DEFAULT NULL,
  primary key(id)
);

alter table scrappie_user
add unique key(email);

alter table scrappie_user
add unique key(username);


create table user_role(
  user_id bigint(20),
  role_id int(11)
);

alter table user_role
  ADD CONSTRAINT FK_user_role_user foreign key (user_id) references scrappie_user(id);

alter table user_role
  ADD CONSTRAINT FK_user_role_role foreign key (role_id) references role(id);

alter table user_role
  ADD UNIQUE KEY UK_user_role(user_id, role_id);
