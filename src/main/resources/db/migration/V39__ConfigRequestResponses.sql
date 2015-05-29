create table configrequest_response (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  response TEXT NOT NULL,
  configrequest_id bigint(20) NOT NULL,
  in_response_to bigint(20) DEFAULT NULL,
  responder_id bigint(20) NOT NULL,
  original_postdate DATETIME NOT NULL,
  last_edit_date DATETIME NOT NULL,
  deleted BOOL NOT NULL DEFAULT FALSE,
  PRIMARY KEY(id)
);

alter table configrequest_response
ADD CONSTRAINT FK_configrequestresponse_responder foreign key (responder_id) references scrappie_user (id);

alter table configrequest_response
ADD CONSTRAINT FK_configrequestresponse_configrequest foreign key (configrequest_id) references configrequest(id);

alter table configrequest_response
ADD CONSTRAINT FK_configrequestresponse_configrequestresponse foreign key (in_response_to) references configrequest_response(id);