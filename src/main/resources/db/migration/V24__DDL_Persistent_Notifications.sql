create table persistent_notification (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  content TEXT NOT NULL,
  title varchar(255) DEFAULT NULL,
  url varchar(100) DEFAULT NULL,
  for_user bigint(20) NOT NULL,
  dateOfPosting datetime NOT NULL,
  readStatus bool DEFAULT false,
  primary key(id)
);

alter table persistent_notification
  ADD CONSTRAINT FK_PN_for_user foreign key (for_user) references scrappie_user (id);