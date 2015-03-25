create table interface_comment (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  post_date datetime NOT NULL,
  comment TEXT NOT NULL,
  interface_type varchar(20) NOT NULL,
  commenter_id bigint(20) NOT NULL,
  wa_id bigint(20) DEFAULT NULL,
  macro_id bigint(20) DEFAULT NULL,
  tmw_id bigint(20) DEFAULT NULL,
  primary key(id)
);

alter table interface_comment
  ADD CONSTRAINT FK_ic_commenter foreign key (commenter_id) references scrappie_user (id);

alter table interface_comment
  ADD CONSTRAINT FK_ic_wa foreign key (wa_id) references weak_aura (id);

alter table interface_comment
  ADD CONSTRAINT FK_ic_macro foreign key (macro_id) references macro (id);

alter table interface_comment
  ADD CONSTRAINT FK_ic_tmw foreign key (tmw_id) references tell_me_when (id);
