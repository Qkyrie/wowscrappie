alter table macro
  add column uploader_id bigint(20);

alter table macro
  ADD CONSTRAINT FK_macro_uploader foreign key (uploader_id) references scrappie_user (id);

alter table tell_me_when
  add column uploader_id bigint(20);

alter table tell_me_when
  ADD CONSTRAINT FK_tmw_uploader foreign key (uploader_id) references scrappie_user (id);

alter table weak_aura
  add column uploader_id bigint(20);

alter table weak_aura
  ADD CONSTRAINT FK_wa_uploader foreign key (uploader_id) references scrappie_user (id);
