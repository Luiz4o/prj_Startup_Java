CREATE TABLE compradores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL UNIQUE,
    nome_completo VARCHAR(255) NOT NULL,
    documento VARCHAR(100) NOT NULL UNIQUE,
    tipo_pessoa VARCHAR(50) NOT NULL,
    CONSTRAINT fk_comprador_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id)
        ON DELETE CASCADE
);