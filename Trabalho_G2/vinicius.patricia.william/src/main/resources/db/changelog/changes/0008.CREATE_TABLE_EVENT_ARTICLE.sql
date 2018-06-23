create table event_article (
  id bigint auto_increment not null,
  event_id bigint not null,
  article_id bigint not null,
  primary key (id),
  unique key UK_EVENT_ARTICLE(event_id, article_id),
  foreign key (event_id) references event(id),
  foreign key (article_id) references article(id)
)
