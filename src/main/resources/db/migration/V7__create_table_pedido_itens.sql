CREATE TABLE pedido_itens (
    id BIGSERIAL PRIMARY KEY,
    id_pedido BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,

    id_produto_original BIGINT,
    nome_produto VARCHAR(255),
    descricao_produto TEXT,
    preco_na_compra DECIMAL(10,2),

    nome_foto VARCHAR(255),
    referencia_foto VARCHAR(500),

    nome_documento VARCHAR(255),
    referencia_doc VARCHAR(500),

    nome_categoria VARCHAR(255),
    nome_lojista VARCHAR(255),

    CONSTRAINT fk_pedido_item_pedido FOREIGN KEY (id_pedido)
        REFERENCES pedidos(id)
        ON DELETE CASCADE
);