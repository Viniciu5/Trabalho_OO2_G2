create table article (
  id bigint auto_increment not null,
  nome varchar(255) not null,
  resumo varchar(255) not null,
  data date not null default '0000-00-00',
  primary key (id),
  unique key UK_ARTICLE(id),
  )
