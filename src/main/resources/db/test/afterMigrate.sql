truncate table usuario restart identity cascade;
truncate table tarefa restart identity cascade;
truncate table objetivo restart identity cascade;
truncate table perfil restart identity cascade;
truncate table categoria restart identity cascade;

insert into usuario(nome, email, senha, data_criacao_conta) values
('João Andrade', 'john@gmail.com', '$2a$10$j6UmB/USQ72BQMAiS736fuAwnNLOAJT/fhcGMXQTKdoiOts2gFSlm', '2021-08-11'),
('Leonardo Leitão', 'leo@gmail.com', '$2a$10$j6UmB/USQ72BQMAiS736fuAwnNLOAJT/fhcGMXQTKdoiOts2gFSlm', '2021-08-11');


insert into perfil(usuario_id, perfis) values
(1, 'CLIENTE'), (1, 'ADMIN'), (2, 'CLIENTE');

insert into categoria (nome, avatar_url, codigo_cor) values
('Saúde', 'data:image/png;base64,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', '#3f3f3f');

insert into objetivo(titulo, data, usuario_id, categoria_id) values
('Tarefa do dia 11/08/2021', '2021-08-11', 1, 1),
('Tarefa do dia 11/08/2021', '2021-08-11', 2, 1);


insert into tarefa(titulo, is_feita, objetivo_id) values
('Jogar lixo fora', true, 1), ('Estudar pra concurso', true, 1),
('Jogar lixo fora', true, 2), ('Estudar pra concurso', true, 2);


