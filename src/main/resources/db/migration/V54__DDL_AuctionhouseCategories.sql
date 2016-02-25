create table auctionhouse_category (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   slug varchar(255) NOT NULL,
   name varchar(255) NOT NULL,
   PRIMARY KEY(id)
);

create table auctionhouse_sub_category (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   slug varchar(255) NOT NULL,
   name varchar(255) NOT NULL,
   category_id bigint(255) NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(category_id) REFERENCES auctionhouse_category(id)
);

create table auctionhouse_subcategory_item (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   subcategory_id bigint(20) NOT NULL,
   item_id bigint(20) NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(item_id) REFERENCES item(id),
   FOREIGN KEY(subcategory_id) REFERENCES auctionhouse_sub_category(id)
);


