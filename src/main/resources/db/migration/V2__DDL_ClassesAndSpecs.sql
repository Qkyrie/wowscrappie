CREATE TABLE wowclass (
  id bigint(20) NOT NULL,
  name varchar(20) DEFAULT NULL,
  slug varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE wowspec (
  id bigint(20) NOT NULL,
  name varchar(20) DEFAULT NULL,
  slug varchar(20) DEFAULT NULL,
  wowclass_id bigint(20) NOT NULL,
  PRIMARY KEY (id)
);

alter table wowspec
  ADD CONSTRAINT FK_spec_class foreign key (wowclass_id) references wowclass (id);