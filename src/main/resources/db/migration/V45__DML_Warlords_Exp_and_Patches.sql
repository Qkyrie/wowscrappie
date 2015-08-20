/*
  never forget YYYY-MM-DD HH:MM:SS
 */
insert into expansion(
  id, name, slug, start_date
) VALUES(
  1, 'Warlords of Draenor', 'warlords_of_draenor', '2014-12-13 00:00:00'
);

insert into patch(
  id, name, main_features, start_date, expansion_id
) VALUES (
  1, '6.0', '[The Iron Tide] Production Release of Warlords of Draenor (prepatches ignored)', '2014-12-13 00:00:00', 1
);

insert into patch(
  id, name, main_features, start_date, expansion_id
) VALUES (
  2, '6.1', '[The Adventure Continues] Garrison Changes, Flight paths, minor changes', '2015-02-24 00:00:00', 1
);

insert into patch(
  id, name, main_features, start_date, expansion_id
) VALUES (
  3, '6.2', '[Fury of Hellfire] Tanaan Jungle, Hellfire Citadel, Shipyards', '2015-06-22 00:00:00', 1
);