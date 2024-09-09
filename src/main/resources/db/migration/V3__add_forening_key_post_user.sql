ALTER TABLE tb_sphere_posts ADD COLUMN user_id BIGINT;

ALTER TABLE tb_sphere_posts
ADD CONSTRAINT fk_user
FOREIGN KEY (user_id) REFERENCES tb_sphere_users(id);

-- Inserir usuários na tabela tb_sphere_users
INSERT INTO tb_sphere_users (name, bio, email, password, created_at, updated_at) VALUES
('Alice Silva', 'Desenvolvedora de software apaixonada por tecnologia.', 'alice.silva@example.com', '$2a$12$jUKEr6VT5DS.fpNx4PCeFui3RiS//xTBaHVoesW4zoIMt/JqYtw0a', '2024-08-25 09:00:00', '2024-08-25 09:00:00'),
('Bruno Souza', 'Engenheiro de software com experiência em front-end.', 'bruno.souza@example.com', '$2a$12$jUKEr6VT5DS.fpNx4PCeFui3RiS//xTBaHVoesW4zoIMt/JqYtw0a', '2024-08-25 10:00:00', '2024-08-25 10:00:00'),
('Carla Ferreira', 'Especialista em segurança cibernética.', 'carla.ferreira@example.com', '$2a$12$jUKEr6VT5DS.fpNx4PCeFui3RiS//xTBaHVoesW4zoIMt/JqYtw0a', '2024-08-25 11:00:00', '2024-08-25 11:00:00'),
('Diego Costa', 'Desenvolvedor full-stack.', 'diego.costa@example.com', '$2a$12$jUKEr6VT5DS.fpNx4PCeFui3RiS//xTBaHVoesW4zoIMt/JqYtw0a', '2024-08-25 12:00:00', '2024-08-25 12:00:00'),
('Eduarda Lima', 'Analista de dados.', 'eduarda.lima@example.com', '$2a$12$jUKEr6VT5DS.fpNx4PCeFui3RiS//xTBaHVoesW4zoIMt/JqYtw0a', '2024-08-25 13:00:00', '2024-08-25 13:00:00');

-- Atualizar a tabela tb_sphere_posts para associar cada post a um usuário
UPDATE tb_sphere_posts SET user_id = 1 WHERE id = 1; -- Primeiro post pertence a Alice Silva
UPDATE tb_sphere_posts SET user_id = 2 WHERE id = 2; -- Segundo post pertence a Bruno Souza
UPDATE tb_sphere_posts SET user_id = 3 WHERE id = 3; -- Terceiro post pertence a Carla Ferreira
UPDATE tb_sphere_posts SET user_id = 4 WHERE id = 4; -- Quarto post pertence a Diego Costa
UPDATE tb_sphere_posts SET user_id = 5 WHERE id = 5; -- Quinto post pertence a Eduarda Lima
