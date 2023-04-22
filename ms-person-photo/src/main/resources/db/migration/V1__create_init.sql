CREATE TABLE person_photo (
  id int AUTO_INCREMENT,
  cpf varchar(255) DEFAULT NULL,
  photo mediumblob,
  PRIMARY KEY (id)
)