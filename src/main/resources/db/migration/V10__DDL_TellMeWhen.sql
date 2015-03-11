CREATE TABLE tell_me_when (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  actual_value TEXT DEFAULT NULL,
  comment TEXT DEFAULT NULL,
  tmw_type varchar(20) NOT NULL,
  spec_id bigint(20) DEFAULT NULL,
  wowclass_id bigint(20) DEFAULT NULL,
  boss_id bigint(20) DEFAULT NULL,
  approved bool DEFAULT FALSE,
  PRIMARY KEY (id)
);

alter table tell_me_when
  ADD CONSTRAINT FK_tmw_spec foreign key (spec_id) references wowspec (id);

alter table tell_me_when
  ADD CONSTRAINT FK_tmw_wowclass foreign key (wowclass_id) references wowclass (id);

alter table tell_me_when
  ADD CONSTRAINT FK_tmw_boss foreign key (boss_id) references boss (id);