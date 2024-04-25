INSERT
INTO
  Recebedor
  (nome)
VALUES
  ('Felipe');

INSERT
INTO
  Recebedor
  (nome)
VALUES
  ('Gabriel');

INSERT
INTO
  Animal
  (nome_provisorio, idade_estimada, raca, especie, data_entrada, condicoes_chegada, recebedor_id, porte)
VALUES
  ('Cachorro 1', 2, 'SRD', 'Cachorro', current_date, 'Saudavel', 1, 'Grande');

INSERT
INTO
  Animal
  (nome_provisorio, idade_estimada, raca, especie, data_entrada, condicoes_chegada, recebedor_id, porte)
VALUES
  ('Gato 1', 3, 'SRD', 'Gato', current_date, 'Saudavel', 2, 'Pequeno');

INSERT
INTO
  Animal
  (nome_provisorio, idade_estimada, raca, especie, data_entrada, condicoes_chegada, recebedor_id, porte)
VALUES
  ('Cachorro 2', 3, 'SRD', 'Cachorro', current_date, 'Pulguento', 1, 'Médio');
