create table user_event (
  id bigint auto_increment not null,
  user_id bigint not null,
  event_id bigint not null,
  primary key (id),
  unique key UK_USER_EVENT(user_id, event_id),
  foreign key (user_id) references user(id),
  foreign key (event_id) references event(id)
)
