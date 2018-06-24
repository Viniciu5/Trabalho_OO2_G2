insert into role(id, name) values(1, 'ROLE_ADMIN');
insert into role(id, name) values(2, 'ROLE_USER');
insert into user(id, username, password, name) values ( 20, 'admin@admin', '$2a$10$x5q0Z1Lr6zU9y78GpfmmNePgzex1n7nQf5rYyGMjsWsKHPC5h6wc.', 'Administrator');
insert into user_role(user_id, role_id) values(20, 1);
insert into event(id, name, abertura, fechamento, user_id) values (20, 'Evento1', null, null, 20);
insert into article(id, titulo, resumo, data, user_id, evento_id) values (20, 'Artigo1', 'Texto para o artigo.', null, 20, 20);