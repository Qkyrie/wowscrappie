-- Add Tier 17
insert into tier(id, name) values(17, 'T17');

-- Add raids for Tier 17 (HM & BRF)
insert into raid(id, name, slug, tier_id) values(1, 'Highmaul', 'highmaul', 17);
insert into raid(id, name, slug, tier_id) values(2, 'Blackrock Foundry', 'blackrock_foundry', 17);

-- Add Bosses for Highmaul
insert into boss(id, name, slug, raid_id, boss_type) values (1, 'Kargath Bladefist', 'kargath_bladefist', 1, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (2, 'The Butcher', 'the_butcher', 1, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (3, 'Brackenspore', 'brackenspore', 1, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (4, 'Tectus, The Living Mountain', 'tectus_the_living_mountain', 1, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (5, 'Twin Ogron', 'twin_ogron', 1, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (6, "Ko'ragh", 'koragh', 1, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (7, "Imperator Mar'gok", 'imperator_margok', 1, 'RAIDBOSS');

-- Add Bosses for Blackrock Foundry
insert into boss(id, name, slug, raid_id, boss_type) values (8, 'Oregorger the Devourer', 'oregorger', 2, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (9, 'Gruul', 'gruul', 2, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (10, 'Blast Furnace', 'blast_furnace', 2, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (11, "Hans'gar & Franzok", 'hans_und_franz', 2, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (12, "Flamebender Ka'graz", 'flamebender', 2, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (13, 'Kromog, Legend of the Mountain', 'kromog', 2, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (14, 'Beastlord Darmac', 'beastlord_darmac', 2, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (15, 'Operator Thogar', 'operator_thogar', 2, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (16, 'The Iron Maidens', 'the_iron_maidens', 2, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (17, 'Blackhand', 'blackhand', 2, 'RAIDBOSS');
