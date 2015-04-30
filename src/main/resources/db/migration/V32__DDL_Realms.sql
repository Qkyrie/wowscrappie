CREATE TABLE realm (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  locality varchar(20) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  slug varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)