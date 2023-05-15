create table login (
  id bigint not null auto_increment,
  id_usuario bigint not null,
  email varchar(255) not null,

  primary key (id)
);