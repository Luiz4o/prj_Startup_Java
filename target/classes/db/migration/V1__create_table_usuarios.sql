CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    documento VARCHAR(100) NOT NULL UNIQUE,
    tipo_pessoa VARCHAR(50) NOT NULL,
    tipo_usuario VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP(6) NOT NULL,
    telefone VARCHAR(50) NOT NULL,
    is_ativo BOOLEAN NOT NULL DEFAULT true,

    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(50),
    bairro VARCHAR(60) NOT NULL,
    cidade VARCHAR(60) NOT NULL,
    estado CHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL
);
