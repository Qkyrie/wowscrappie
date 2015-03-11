CREATE TABLE adminmessage (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dateOfPosting datetime DEFAULT NULL,
  message varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

