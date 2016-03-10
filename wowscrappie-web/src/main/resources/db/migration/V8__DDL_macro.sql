CREATE TABLE macro (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  actual_value TEXT DEFAULT NULL,
  description TEXT DEFAULT NULL,
  macro_type varchar(20) NOT NULL,
  boss_id bigint(20) DEFAULT NULL,
  wowspec_id bigint(20) DEFAULT NULL,
  wowclass_id bigint(20) DEFAULT NULL,
  approved bool DEFAULT FALSE,
  PRIMARY KEY (id)
);

alter table macro
  ADD CONSTRAINT FK_macro_spec foreign key (wowspec_id) references wowspec (id);

alter table macro
  ADD CONSTRAINT FK_macro_boss foreign key (boss_id) references boss (id);

  alter table macro
  ADD CONSTRAINT FK_macro_wowclass foreign key (wowclass_id) references wowclass (id);