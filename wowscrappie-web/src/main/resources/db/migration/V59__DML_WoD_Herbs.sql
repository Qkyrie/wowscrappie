-- adding 109127, 109124, 109125, 109128, 109129

INSERT IGNORE INTO item (id, name) VALUES (109124, 'Frostweed');
INSERT IGNORE INTO item (id, name) VALUES (109125, 'Fireweed');
INSERT IGNORE INTO item (id, name) VALUES (109126, 'Gorgrond Flytrap');
INSERT IGNORE INTO item (id, name) VALUES (109127, 'Starflower');
INSERT IGNORE INTO item (id, name) VALUES (109128, 'Nagrand Arrowbloom');
INSERT IGNORE INTO item (id, name) VALUES (109129, 'Talador Orchid');


insert into auctionhouse_subcategory_item(id, subcategory_id, item_id) VALUES(
  null,
  (select id from auctionhouse_sub_category where slug = 'wod_herbs'),
  109124
);

insert into auctionhouse_subcategory_item(id, subcategory_id, item_id) VALUES(
  null,
  (select id from auctionhouse_sub_category where slug = 'wod_herbs'),
  109125
);

insert into auctionhouse_subcategory_item(id, subcategory_id, item_id) VALUES(
  null,
  (select id from auctionhouse_sub_category where slug = 'wod_herbs'),
  109126
);

insert into auctionhouse_subcategory_item(id, subcategory_id, item_id) VALUES(
  null,
  (select id from auctionhouse_sub_category where slug = 'wod_herbs'),
  109127
);

insert into auctionhouse_subcategory_item(id, subcategory_id, item_id) VALUES(
  null,
  (select id from auctionhouse_sub_category where slug = 'wod_herbs'),
  109128
);

insert into auctionhouse_subcategory_item(id, subcategory_id, item_id) VALUES(
  null,
  (select id from auctionhouse_sub_category where slug = 'wod_herbs'),
  109129
);
