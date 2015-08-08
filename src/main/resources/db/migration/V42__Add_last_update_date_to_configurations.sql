alter table weak_aura
  add column last_update_date DATETIME;

alter table macro
  add column last_update_date DATETIME;

alter table tell_me_when
  add column last_update_date DATETIME;
