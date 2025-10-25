CREATE TABLE produtos (
    id BIGSERIAL PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    quantidade_estoque INT NOT NULL,
    CONSTRAINT fk_produto_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id),
    CONSTRAINT fk_produto_categoria FOREIGN KEY (id_categoria)
        REFERENCES categorias(id)
);