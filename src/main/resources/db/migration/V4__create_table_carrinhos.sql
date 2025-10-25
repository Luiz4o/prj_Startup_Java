CREATE TABLE carrinhos (
    id BIGSERIAL PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    status VARCHAR(50),
    CONSTRAINT fk_carrinho_usuarios FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id) ON DELETE CASCADE
);