insert into auctionhouse_category(id, slug, name) VALUES (null, 'herbalism', 'Herbalism');
insert into auctionhouse_sub_category(id, slug, name, category_id, added_on) values (null, 'wod_herbs', 'Warlords of Draenor Herbs',
(select id from auctionhouse_category where slug = 'herbalism'),
'2014-11-13 00:00:00'
);

insert into auctionhouse_sub_category(id, slug, name, category_id, added_on) values (null, 'mop_herbs', 'Mists of Pandaria Herbs',
(select id from auctionhouse_category where slug = 'herbalism'),
'2012-09-25 00:00:00'
);

insert into auctionhouse_sub_category(id, slug, name, category_id, added_on) values (null, 'cata_herbs', 'Cataclysm Herbs',
(select id from auctionhouse_category where slug = 'herbalism'),
'2010-12-07 00:00:00'
);

insert into auctionhouse_sub_category(id, slug, name, category_id, added_on) values (null, 'wotlk_herbs', 'Wrath of the Lich King Herbs',
(select id from auctionhouse_category where slug = 'herbalism'),
'2008-11-13 00:00:00'
);

insert into auctionhouse_sub_category(id, slug, name, category_id, added_on) values (null, 'bc_herbs', 'Burning Crusade Herbs',
(select id from auctionhouse_category where slug = 'herbalism'),
'2007-01-16 00:00:00'
);

insert into auctionhouse_sub_category(id, slug, name, category_id, added_on) values (null, 'vanilla_herbs', 'Vanilla Herbs',
(select id from auctionhouse_category where slug = 'herbalism'),
'2004-11-23 00:00:00'
);
