create table event (
  id bigint auto_increment not null,
  nome varchar(255) not null,
  abertura date not null default '0000-00-00',
  fechamento date not null default '0000-00-00',
  primary key (id),
  unique key UK_EVENT(id),
  )
