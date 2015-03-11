CREATE TABLE weak_aura (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  actual_value TEXT DEFAULT NULL,
  comment TEXT DEFAULT NULL,
  wa_type varchar(20) NOT NULL,
  spec_id bigint(20) DEFAULT NULL,
  wowclass_id bigint(20) DEFAULT NULL,
  boss_id bigint(20) DEFAULT NULL,
  approved bool DEFAULT FALSE,
  PRIMARY KEY (id)
);

alter table weak_aura
  ADD CONSTRAINT FK_wa_spec foreign key (spec_id) references wowspec (id);

alter table weak_aura
  ADD CONSTRAINT FK_wa_wowclass foreign key (wowclass_id) references wowclass (id);

alter table weak_aura
  ADD CONSTRAINT FK_wa_boss foreign key (boss_id) references boss (id);
