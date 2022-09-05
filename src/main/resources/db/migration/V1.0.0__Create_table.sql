CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE producer
(
    id   uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

CREATE TABLE product
(
    id          uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name        VARCHAR(200)   NOT NULL,
    price       NUMERIC(10, 2) NOT NULL,
    id_producer uuid,
    FOREIGN KEY (id_producer) REFERENCES producer (id)

);

CREATE TABLE role
(
    id   uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name varchar(250) not null UNIQUE
);

CREATE TABLE users
(
    id         uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    email      varchar(250) not null UNIQUE,
    password   varchar(250) not null,
    first_name varchar(250) not null,
    last_name  varchar(250) not null,
    id_role uuid,
    FOREIGN KEY (id_role) REFERENCES role (id)
);
