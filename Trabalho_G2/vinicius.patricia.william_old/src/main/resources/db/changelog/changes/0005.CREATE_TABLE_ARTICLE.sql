create table article (
  id bigint auto_increment not null,
  titulo varchar(255) not null,
  resumo varchar(255) not null,
  data date,
  primary key (id),
  unique key UK_ARTICLE(id)
  )
