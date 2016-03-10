create table rating (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  rating_type varchar(20) NOT NULL,
  macro_id bigint(20) DEFAULT NULL,
  tmw_id bigint(20) DEFAULT NULL,
  wa_id bigint(20) DEFAULT NULL,
  PRIMARY KEY(id)
);

alter table rating
  ADD CONSTRAINT FK_rating_macro foreign key (macro_id) references macro (id);

alter table rating
  ADD CONSTRAINT FK_rating_tmw foreign key (tmw_id) references tell_me_when (id);

alter table rating
  ADD CONSTRAINT FK_rating_wa foreign key (wa_id) references weak_aura (id);


create table positive_rating_user(
  rating_id bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,
  primary key(rating_id, user_id)
);

alter table positive_rating_user
  add constraint fk_positive_rating_user_rating foreign key (rating_id) references rating (id);

alter table positive_rating_user
  add constraint fk_positive_rating_user_user foreign key (user_id) references scrappie_user (id);

create table negative_rating_user(
  rating_id bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,
  primary key(rating_id, user_id)
);

alter table negative_rating_user
  add constraint fk_negative_rating_user_rating foreign key (rating_id) references rating (id);

alter table negative_rating_user
  add constraint fk_negative_rating_user_user foreign key (user_id) references scrappie_user (id);

