CREATE TABLE pedido_itens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pedido BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_pedido_item_pedido FOREIGN KEY (id_pedido)
        REFERENCES pedidos(id) ON DELETE CASCADE,
    CONSTRAINT fk_pedido_item_produto FOREIGN KEY (id_produto)
        REFERENCES produtos(id)
);
