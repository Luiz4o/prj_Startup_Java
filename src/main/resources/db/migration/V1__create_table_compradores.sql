CREATE TABLE compradores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    documento VARCHAR(100) NOT NULL UNIQUE,
    tipo_pessoa VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    data_criacao DATETIME NOT NULL,
    telefone VARCHAR(50) NOT NULL
);