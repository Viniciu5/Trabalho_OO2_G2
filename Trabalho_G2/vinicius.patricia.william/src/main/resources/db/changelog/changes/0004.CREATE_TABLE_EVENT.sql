create table event (
  id bigint auto_increment not null,
  name varchar(255) not null,
  abertura date,
  fechamento date,
  primary key (id),
  unique key UK_EVENT(id)
  )
