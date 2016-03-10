CREATE TABLE boss (
  id bigint(20) NOT NULL,
  name varchar(30) DEFAULT NULL,
  slug varchar(30) DEFAULT NULL,
  raid_id bigint(20) DEFAULT NULL,
  boss_type varchar(20) NOT NULL,
  PRIMARY KEY (id)
);

alter table boss
  ADD CONSTRAINT FK_boss_raid foreign key (raid_id) references raid (id);