create table configrequest (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  title varchar(300) NOT NULL,
  question TEXT NOT NULL,
  original_postdate DATETIME NOT NULL,
  last_edit_date DATETIME NOT NULL,
  deleted BOOL NOT NULL DEFAULT FALSE,
  poster_id bigint(20) NOT NULL,
  PRIMARY KEY(id)
);

alter table configrequest
ADD CONSTRAINT FK_configrequest_poster foreign key (poster_id) references scrappie_user (id);



