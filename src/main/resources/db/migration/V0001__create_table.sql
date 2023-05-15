create table usuario (
  id bigint not null auto_increment,
  nome varchar(60) not null,
  email varchar(255) not null,
  senha1 varchar(2000) not null,
  telefone varchar(20),
  descricao varchar(300),
  account_creation_date DATE,
  account_deactivation_date DATE,

  primary key (id)
);