CREATE TABLE auctionhousesnapshot (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  minimum_bid double DEFAULT NULL,
  minimum_buyout double DEFAULT NULL,
  maximum_bid double DEFAULT NULL,
  maximum_buyout double DEFAULT NULL,
  estimatedDemand double DEFAULT NULL,
  average_bid double DEFAULT NULL,
  average_buyout double DEFAULT NULL,
  exportTime DATETIME DEFAULT NULL,
  quantity bigint(20) DEFAULT NULL,
  item_id bigint(20) DEFAULT NULL,
  realm_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

alter table auctionhousesnapshot
  ADD CONSTRAINT FK_ahsnapshot_item foreign key (item_id) references item(id);

alter table auctionhousesnapshot
  ADD CONSTRAINT FK_ahsnapshot_realm foreign key (realm_id) references realm(id);
