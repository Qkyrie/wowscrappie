CREATE TABLE raid (
  id bigint(20) NOT NULL,
  name varchar(30) DEFAULT NULL,
  slug varchar(30) DEFAULT NULL,
  tier_id bigint(20) NOT NULL,
  PRIMARY KEY (id)
);

alter table raid
  ADD CONSTRAINT FK_raid_tier foreign key (tier_id) references tier (id);