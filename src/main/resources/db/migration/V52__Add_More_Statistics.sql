alter table auctionhousesnapshot
  ADD median_bid DOUBLE DEFAULT NULL;

alter table auctionhousesnapshot
  ADD median_buyout DOUBLE DEFAULT NULL;

alter table auctionhousesnapshot
  ADD stdev_bid DOUBLE DEFAULT NULL;

alter table auctionhousesnapshot
  ADD stdev_buyout DOUBLE DEFAULT NULL;
