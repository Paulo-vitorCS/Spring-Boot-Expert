CREATE TABLE city (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    inhabitants BIGINT
);

INSERT INTO city
    (id, name, inhabitants)
VALUES
    (1, 'Uberlândia', 700000),
    (2, 'São Paulo', 12400000),
    (3, 'Catalão', 120000),
    (4, 'Ouvidor', 30000),
    (5, 'Fortaleza', 300000),
    (6, 'Salvador', 123000),
    (7, 'Porto Alegre', 770000),
    (8, 'Porto Velho', 12400000),
    (9, 'Palmas', 127000),
    (10, 'Natal', 750000),
    (11, 'Brasilia', 80000),
    (12, 'Araguari', 130000);