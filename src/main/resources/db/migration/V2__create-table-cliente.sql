CREATE TABLE cliente (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL unique ,
  cpf VARCHAR(11) NOT NULL unique,
  data_nascimento DATE NOT NULL,
  PRIMARY KEY (id))
;