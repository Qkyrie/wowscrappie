create table private_message(
   id bigint(20) NOT NULL AUTO_INCREMENT,
   dateOfPosting datetime NOT NULL,
   content TEXT NOT NULL,
   title varchar(200) NOT NULL,
   from_user_id bigint(20) NOT NULL,
   to_user_id bigint(20) NOT NULL,
   response_to_private_message_id bigint(20) DEFAULT NULL,
   PRIMARY KEY (id)
);

alter table private_message
  ADD CONSTRAINT FK_PM_FROM_USER foreign key (from_user_id) references scrappie_user (id);

alter table private_message
  ADD CONSTRAINT FK_PM_TO_USER foreign key (to_user_id) references scrappie_user (id);

alter table private_message
  ADD CONSTRAINT FK_PM_RESP_TO_PM foreign key (response_to_private_message_id) references private_message (id);