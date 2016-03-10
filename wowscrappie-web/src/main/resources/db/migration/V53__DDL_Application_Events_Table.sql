create table application_events (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   event_type varchar(255) NOT NULL,
   event_time DATETIME NOT NULL,
   event_message TEXT NOT NULL,
   PRIMARY KEY(id)
);

