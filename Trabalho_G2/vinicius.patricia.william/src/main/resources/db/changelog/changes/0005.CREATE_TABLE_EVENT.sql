create table event (
  id bigint auto_increment not null,
  artigo varchar(255) not null,
  primary key (id),
  unique key UK_EVENT(id),
  foreign key (user_id) references user(id))
