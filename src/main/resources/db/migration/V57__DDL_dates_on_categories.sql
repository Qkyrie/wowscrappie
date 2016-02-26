alter table auctionhouse_category
  add column added_on DATETIME DEFAULT NULL;

alter table auctionhouse_sub_category
  add column added_on DATETIME DEFAULT NULL;