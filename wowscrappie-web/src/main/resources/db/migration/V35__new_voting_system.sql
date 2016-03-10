create table rating_by_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  rating_id bigint(20) NOT NULL,
  user_id bigint(20) not null,
  rating varchar(20) DEFAULT NULL,
  PRIMARY KEY(id)
);

alter table rating_by_user
  ADD CONSTRAINT FK_rbu_rating foreign key (rating_id) references rating (id);

alter table rating_by_user
  ADD CONSTRAINT FK_rbu_user foreign key (user_id) references scrappie_user (id);