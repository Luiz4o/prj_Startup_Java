CREATE TABLE pedidos (
    id BIGSERIAL PRIMARY KEY,
    id_carrinho BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    valor_total DECIMAL(10,2),
    status_pedido VARCHAR(50),
    data_pedido TIMESTAMP,
    ultima_atualizacao TIMESTAMP,

    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(50),
    bairro VARCHAR(60) NOT NULL,
    cidade VARCHAR(60) NOT NULL,
    estado CHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL,

    CONSTRAINT fk_pedido_carrinho FOREIGN KEY (id_carrinho)
        REFERENCES carrinhos(id) ON DELETE CASCADE,

    CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id)
);