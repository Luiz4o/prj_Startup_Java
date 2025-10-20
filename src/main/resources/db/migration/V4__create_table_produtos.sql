CREATE TABLE produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_lojista BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    quantidade_estoque INT NOT NULL,
    CONSTRAINT fk_produto_lojista FOREIGN KEY (id_lojista)
        REFERENCES lojistas(id),
    CONSTRAINT fk_produto_categoria FOREIGN KEY (id_categoria)
        REFERENCES categorias(id)
);
