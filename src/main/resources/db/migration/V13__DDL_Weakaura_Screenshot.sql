CREATE TABLE screenshot (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  caption varchar(255) DEFAULT NULL,
  reference varchar(255) NOT NULL,
  wa_id bigint(20) DEFAULT NULL,
  tmw_id bigint(20) DEFAULT NULL,
  ss_type varchar(10) NOT NULL,
  PRIMARY KEY (id)
);

alter table screenshot
  ADD CONSTRAINT FK_screenshot_wa foreign key (wa_id) references weak_aura (id);

alter table screenshot
  ADD CONSTRAINT FK_screenshot_tmw foreign key (tmw_id) references tell_me_when (id);