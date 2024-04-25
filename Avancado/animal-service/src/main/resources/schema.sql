CREATE TABLE Recebedor (
  id INT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NOT NULL,
   CONSTRAINT pk_recebedor PRIMARY KEY (id)
);

CREATE TABLE Animal (
  id INT AUTO_INCREMENT NOT NULL,
   nome_provisorio VARCHAR(255) NOT NULL,
   idade_estimada INT NOT NULL,
   raca VARCHAR(255) NOT NULL,
   especie VARCHAR(255) NOT NULL,
   data_entrada date NOT NULL,
   data_adocao date NULL,
   condicoes_chegada VARCHAR(255) NOT NULL,
   recebedor_id INT NOT NULL,
   data_obito date NULL,
   porte VARCHAR(255) NOT NULL,
   CONSTRAINT pk_animal PRIMARY KEY (id)
);

ALTER TABLE Animal ADD CONSTRAINT FK_ANIMAL_ON_RECEBEDOR FOREIGN KEY (recebedor_id) REFERENCES Recebedor (id);