ALTER TABLE tb_sphere_users
    ADD COLUMN avatar VARCHAR(255);

UPDATE tb_sphere_users SET avatar = 'https://avatar.iran.liara.run/public' WHERE id = 1;
UPDATE tb_sphere_users SET avatar = 'https://avatar.iran.liara.run/public' WHERE id = 2;
UPDATE tb_sphere_users SET avatar = 'https://avatar.iran.liara.run/public' WHERE id = 3;
UPDATE tb_sphere_users SET avatar = 'https://avatar.iran.liara.run/public' WHERE id = 4;
UPDATE tb_sphere_users SET avatar = 'https://avatar.iran.liara.run/public' WHERE id = 5;