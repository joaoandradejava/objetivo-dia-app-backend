truncate table usuario restart identity cascade;
truncate table tarefa restart identity cascade;
truncate table objetivo restart identity cascade;
truncate table perfil restart identity cascade;

insert into usuario(nome, email, senha, data_criacao_conta) values
('João Andrade', 'john@gmail.com', '123', '2021-08-11');

insert into perfil(usuario_id, perfis) values
(1, 'CLIENTE');

insert into objetivo(titulo, data, usuario_id) values
('Tarefa do dia 11/08/2021', '2021-08-11', 1);

insert into tarefa(titulo, is_feita, objetivo_id) values
('Jogar lixo fora', true, 1), ('Estudar pra concurso', true, 1);

