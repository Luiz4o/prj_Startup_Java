CREATE TABLE carrinhos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_comprador BIGINT NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(50),
    CONSTRAINT fk_carrinho_comprador FOREIGN KEY (id_comprador)
        REFERENCES compradores(id) ON DELETE CASCADE
);
