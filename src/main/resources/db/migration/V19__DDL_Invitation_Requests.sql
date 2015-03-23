create table invitation_request(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    reason TEXT NOT NULL,
    email varchar(100) NOT NULL,
    PRIMARY KEY(id)
);