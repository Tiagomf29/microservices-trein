CREATE TABLE nationality (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

insert into nationality
(id,nome)
values
(1,'Brasil');

CREATE TABLE person (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(80) NOT NULL,
  nascimento date NOT NULL,
  nome_mae varchar(80) DEFAULT NULL,
  nome_pai varchar(80) DEFAULT NULL,
  cpf varchar(14) NOT NULL,
  id_nationality int DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uc_person (cpf),
  KEY fk_person_n1 (id_nationality),
  CONSTRAINT fk_person_n1 FOREIGN KEY (id_nationality) REFERENCES nationality (id)
);

insert into person
(nome, nascimento,nome_mae,nome_pai,cpf,id_nationality)
values
('Tiago','1986-05-29','Lidia','Juarez','07008488602',1);
