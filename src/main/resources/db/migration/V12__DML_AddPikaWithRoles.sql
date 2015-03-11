insert into role(id, authority) values(1, 'ROLE_ADMIN');
insert into role(id, authority) values(2, 'ROLE_USER');

insert into scrappie_user(username, password, email)
VALUES ('Pikachü', '57c27aece81d34fdc1f4c58225882b91adba045aa2c5ccc6712629b79d0efe3c2d8a273b3b3a4bdd', 'quintendeswaef@gmail.com');

insert into user_role(user_id, role_id) values(
(select id from scrappie_user where email = 'quintendeswaef@gmail.com'), 1
);

insert into user_role(user_id, role_id) values(
(select id from scrappie_user where email = 'quintendeswaef@gmail.com'), 2
);