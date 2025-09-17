CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_carrinho BIGINT NOT NULL,
    id_comprador BIGINT NOT NULL,
    id_endereco BIGINT NOT NULL,
    valor_total DECIMAL(10,2),
    status_pedido VARCHAR(50),
    data_pedido DATETIME,
    ultima_atualizacao DATETIME,
    CONSTRAINT fk_pedido_carrinho FOREIGN KEY (id_carrinho)
        REFERENCES carrinhos(id) ON DELETE CASCADE,
    CONSTRAINT fk_pedido_comprador FOREIGN KEY (id_comprador)
        REFERENCES compradores(id),
    CONSTRAINT fk_pedido_endereco FOREIGN KEY (id_endereco)
        REFERENCES enderecos(id)
);
