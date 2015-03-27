create table interface_report (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  post_date datetime NOT NULL,
  comment TEXT NOT NULL,
  handled bool DEFAULT FALSE,
  interface_type varchar(20) DEFAULT NULL,
  reporter_id bigint(20) DEFAULT NULL,
  wa_id bigint(20) DEFAULT NULL,
  macro_id bigint(20) DEFAULT NULL,
  tmw_id bigint(20) DEFAULT NULL,
  primary key(id)
);

alter table interface_report
  ADD CONSTRAINT FK_ir_commenter foreign key (reporter_id) references scrappie_user (id);

alter table interface_report
  ADD CONSTRAINT FK_ir_wa foreign key (wa_id) references weak_aura (id);

alter table interface_report
  ADD CONSTRAINT FK_ir_macro foreign key (macro_id) references macro (id);

alter table interface_report
  ADD CONSTRAINT FK_ir_tmw foreign key (tmw_id) references tell_me_when (id);
