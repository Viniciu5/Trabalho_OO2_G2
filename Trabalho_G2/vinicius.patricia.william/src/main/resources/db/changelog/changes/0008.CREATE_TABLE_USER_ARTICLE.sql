create table user_article (
  id bigint auto_increment not null,
  user_id bigint not null,
  article_id bigint not null,
  primary key (id),
  unique key UK_USER_ARTICLE(user_id, article_id),
  foreign key (user_id) references user(id),
  foreign key (article_id) references article(id)
)
