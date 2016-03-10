-- Add Tier 18
insert into tier(id, name) values(18, 'T18');
insert into raid(id, name, slug, tier_id) values(3, 'Hellfire Citadel', 'hellfire_citadel', 18);

--Add bosses for hfc, starting from 18, blackhand was 17
insert into boss(id, name, slug, raid_id, boss_type) values (18, 'Hellfire Assault', 'hellfire_assault', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (19, 'Iron Reaver', 'iron_reaver', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (20, 'Kormrok', 'kormrok', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (21, 'Hellfire High Council', 'hellfire_high_council', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (22, 'Kilrogg Deadeye', 'kilrogg_deadeye', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (23, 'Gorefiend', 'gorefiend', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (24, 'Shadow-Lord Iskar', 'shadow_lord_iskar', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (25, 'Fel Lord Zakuun', 'fel_lord_zakuun', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (26, "Xhul'horac", 'xhulhorac', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (27, 'Socrethar the Eternal', 'socretar_the_eternal', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (28, 'Tyrant Velhari', 'tyrant_velhari', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (29, 'Mannoroth', 'mannoroth', 3, 'RAIDBOSS');
insert into boss(id, name, slug, raid_id, boss_type) values (30, 'Archimonde', 'archimonde', 3, 'RAIDBOSS');
