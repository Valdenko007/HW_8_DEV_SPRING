INSERT INTO role(name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO users(email, password, first_name, last_name,id_role)
VALUES ('admin@gmail.com', '{bcrypt}$2b$10$g65sVAW6ZvKvrHUqkWg3WuccCc9IPAW59FMfCsvBTIiIvxKcw3mEW', 'admin', 'admin',(SELECT id FROM role where name = 'ADMIN')),
       ('user@gmail.com','{bcrypt}$2a$10$cPbZZcV2rLwtjKPBfbl/ieGTO5ovRRHXiHb07lMY9nL52Rz/dQcQ2','user','user', (SELECT id FROM role where name = 'USER'));



INSERT INTO producer (name)
VALUES ('Ford'),
       ('Volkswagen'),
       ('Honda'),
       ('Mitsubishi'),
       ('Audi');

INSERT INTO product (name, price, id_producer)
VALUES ('Ford Edge', 22223.37, (SELECT id FROM producer where name = 'Ford')),
       ('Ford Fiesta', 15223.37, (SELECT id FROM producer where name = 'Ford')),
       ('Ford Focus', 1583.76, (SELECT id FROM producer where name = 'Ford')),
       ('Ford Mondeo', 18783.76, (SELECT id FROM producer where name = 'Ford')),
       ('Ford Ranger', 17783.76, (SELECT id FROM producer where name = 'Ford')),
       ('Volkswagen Jetta', 10458.63, (SELECT id FROM producer where name = 'Volkswagen')),
       ('Volkswagen Passat', 15279.22, (SELECT id FROM producer where name = 'Volkswagen')),
       ('Volkswagen Golf', 12167.53, (SELECT id FROM producer where name = 'Volkswagen')),
       ('Volkswagen Tiguan', 20279.22, (SELECT id FROM producer where name = 'Volkswagen')),
       ('Volkswagen Polo', 8279.22, (SELECT id FROM producer where name = 'Volkswagen')),
       ('Honda CR-V', 15279.22, (SELECT id FROM producer where name = 'Honda')),
       ('Honda Civic', 16279.22, (SELECT id FROM producer where name = 'Honda')),
       ('Honda Accord', 15279.22, (SELECT id FROM producer where name = 'Honda')),
       ('Honda NSX', 11162.22, (SELECT id FROM producer where name = 'Honda')),
       ('Honda S2000', 10458.47, (SELECT id FROM producer where name = 'Honda')),
       ('Mitsubishi GTO', 18224.49, (SELECT id FROM producer where name = 'Mitsubishi')),
       ('Mitsubishi Dion', 8195.73, (SELECT id FROM producer where name = 'Mitsubishi')),
       ('Mitsubishi Delica', 10109.73, (SELECT id FROM producer where name = 'Mitsubishi')),
       ('Audi TT', 20271.68, (SELECT id FROM producer where name = 'Audi')),
       ('Audi A4', 10199.92, (SELECT id FROM producer where name = 'Audi')),
       ('Audi Q7', 20199.92, (SELECT id FROM producer where name = 'Audi'));
