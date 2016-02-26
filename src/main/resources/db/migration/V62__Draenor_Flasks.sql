-- draenic agility flask (109145)
INSERT IGNORE INTO item (id, name) VALUES (109145, 'Draenic Agility Flask');

insert into recipes(id, name, creates_item_id, creates_amount) values (
    null, 'Recipe: Draenic Agility Flask', 109145, 1
);
-- 4 fireweed
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109125, 4, id from recipes where creates_item_id = 109145;

-- 4 starflower
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109127, 4, id from recipes where creates_item_id = 109145;


-- draenic intellect flask (109147)
INSERT IGNORE INTO item (id, name) VALUES (109147, 'Draenic Intellect Flask');

insert into recipes(id, name, creates_item_id, creates_amount) values (
  null, 'Recipe: Draenic Intellect Flask', 109147, 1
);

-- talador orchir
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109129, 4, id from recipes where creates_item_id = 109147;

-- 4 starflower
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109127, 4, id from recipes where creates_item_id = 109147;


-- draenic stamina flask (109152)
INSERT IGNORE INTO item (id, name) VALUES (109152, 'Draenic Stamina Flask');

insert into recipes(id, name, creates_item_id, creates_amount) values (
  null, 'Recipe: Draenic Stamina Flask', 109152, 1
);

-- Nagrand Arrowbloom
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109128, 4, id from recipes where creates_item_id = 109152;

-- 4 starflower
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109127, 4, id from recipes where creates_item_id = 109152;


-- draenic strength flask (109148)
INSERT IGNORE INTO item (id, name) VALUES (109148, 'Draenic Strength Flask');

insert into recipes(id, name, creates_item_id, creates_amount) values (
  null, 'Recipe: Draenic Strength Flask', 109148, 1
);

-- Nagrand Arrowbloom
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109128, 4, id from recipes where creates_item_id = 109148;

-- 4 Gorgrond FlyTrap
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109126, 4, id from recipes where creates_item_id = 109148;
