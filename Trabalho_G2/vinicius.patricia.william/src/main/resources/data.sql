insert into role(id, name) values(1, 'ROLE_ADMIN');
insert into role(id, name) values(2, 'ROLE_USER');
insert into user(id, username, email, nome, password) values (1, 'admin', 'admin@admin.com', 'Administrator', 'admin');
insert into user_role(user_id, role_id) values(1, 1);
insert into event(id, artigo) values (1, 'Texto');