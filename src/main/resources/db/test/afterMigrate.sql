truncate table usuario restart identity cascade;
truncate table tarefa restart identity cascade;
truncate table objetivo restart identity cascade;
truncate table perfil restart identity cascade;

insert into usuario(nome, email, senha, data_criacao_conta) values
('João Andrade', 'john@gmail.com', '$2a$10$j6UmB/USQ72BQMAiS736fuAwnNLOAJT/fhcGMXQTKdoiOts2gFSlm', '2021-08-11'),
('Leonardo Leitão', 'leo@gmail.com', '$2a$10$j6UmB/USQ72BQMAiS736fuAwnNLOAJT/fhcGMXQTKdoiOts2gFSlm', '2021-08-11');


insert into perfil(usuario_id, perfis) values
(1, 'CLIENTE'), (2, 'CLIENTE');

insert into objetivo(titulo, data, usuario_id) values
('Tarefa do dia 11/08/2021', '2021-08-11', 1),
('Tarefa do dia 11/08/2021', '2021-08-11', 2);


insert into tarefa(titulo, is_feita, objetivo_id) values
('Jogar lixo fora', true, 1), ('Estudar pra concurso', true, 1),
('Jogar lixo fora', true, 2), ('Estudar pra concurso', true, 2);


