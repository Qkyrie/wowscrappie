INSERT IGNORE INTO item (id, name) VALUES (113264, 'Sorcerous Air');
INSERT IGNORE INTO item (id, name) VALUES (113263, 'Sorcerous Earth');
INSERT IGNORE INTO item (id, name) VALUES (113261, 'Sorcerous Fire');
INSERT IGNORE INTO item (id, name) VALUES (113262, 'Sorcerous Water');
INSERT IGNORE INTO item (id, name) VALUES (108996, 'Alchemical Catalyst');

INSERT IGNORE INTO item (id, name) VALUES (109153, 'Greater Draenic Agility Flask');
insert into recipes(id, name, creates_item_id, creates_amount) values(
    null, 'Recipe: Greater Draenic Agility Flask',109153, 5
);
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 108996, 5, id from recipes where creates_item_id = 109153;
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 113262, 1, id from recipes where creates_item_id = 109153;
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109145, 5, id from recipes where creates_item_id = 109153;


INSERT IGNORE INTO item (id, name) VALUES (109155, 'Greater Draenic Intellect Flask');
insert into recipes(id, name, creates_item_id, creates_amount) values(
  null, 'Recipe: Greater Draenic Intellect Flask',109155, 5
);
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 108996, 5, id from recipes where creates_item_id = 109155;
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 113261, 1, id from recipes where creates_item_id = 109155;
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109147, 5, id from recipes where creates_item_id = 109155;


-- greater draenic stamina flask (109160)
INSERT IGNORE INTO item (id, name) VALUES (109160, 'Greater Draenic Stamina Flask');
insert into recipes(id, name, creates_item_id, creates_amount) values(
  null, 'Recipe: Greater Draenic Stamina Flask',109160, 5
);
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 108996, 5, id from recipes where creates_item_id = 109160;
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 113262, 1, id from recipes where creates_item_id = 109160;
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109152, 5, id from recipes where creates_item_id = 109160;



-- greater draenic strength flask (109156)
INSERT IGNORE INTO item (id, name) VALUES (109156, 'Greater Draenic Strength Flask');
insert into recipes(id, name, creates_item_id, creates_amount) values(
  null, 'Recipe: Greater Draenic Strength Flask',109156, 5
);
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 108996, 5, id from recipes where creates_item_id = 109156;
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 113261, 1, id from recipes where creates_item_id = 109156;
insert into recipe_item(id, item_id, amount, recipe_id)
  select null, 109148, 5, id from recipes where creates_item_id = 109156;
