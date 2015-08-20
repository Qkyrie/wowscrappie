create table expansion (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  slug varchar(100) NOT NULL,
  start_date DATETIME NOT NULL,
  PRIMARY KEY(id)
);

create table patch (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  main_features TEXT NOT NULL,
  start_date DATETIME NOT NULL,
  expansion_id bigint(20) NOT NULL,
  PRIMARY KEY(id)
);

alter table patch
  add constraint fk_patch_expansion foreign key (expansion_id) REFERENCES expansion(id);