create table auctionhousesnapshot_realm_configuration (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    realm_id bigint(20) DEFAULT NULL,
    last_update DATETIME DEFAULT NULL,
    needs_update boolean NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

alter table auctionhousesnapshot_realm_configuration
  ADD CONSTRAINT FK_GEN_SS_CONFIG_realm foreign key (realm_id) references realm (id);