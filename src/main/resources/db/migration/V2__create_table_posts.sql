CREATE TABLE tb_sphere_posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255),
    created_at TIMESTAMP
);

INSERT INTO tb_sphere_posts (text, created_at) VALUES ('Primeiro post', '2024-08-26 10:00:00');
INSERT INTO tb_sphere_posts (text, created_at) VALUES ('Segundo post', '2024-08-26 11:00:00');
INSERT INTO tb_sphere_posts (text, created_at) VALUES ('Terceiro post', '2024-08-26 12:00:00');
INSERT INTO tb_sphere_posts (text, created_at) VALUES ('Quarto post', '2024-08-26 13:00:00');
INSERT INTO tb_sphere_posts (text, created_at) VALUES ('Quinto post', '2024-08-26 14:00:00');