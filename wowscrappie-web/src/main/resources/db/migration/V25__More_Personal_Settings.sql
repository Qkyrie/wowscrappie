alter table user_profile
  add column receive_email_notifications bool DEFAULT false;

alter table user_profile
  add column sound_repository varchar(20) not null default 'DEFAULT';