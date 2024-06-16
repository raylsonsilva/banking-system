-- Criação da tabela Banco
CREATE TABLE banco (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       codigo VARCHAR(3) NOT NULL,
                       nome VARCHAR(50) NOT NULL
);

-- Inserção de dados na tabela Banco
INSERT INTO banco (id, codigo, nome) VALUES (1, '001', 'Banco do Brasil');
INSERT INTO banco (id, codigo, nome) VALUES (2, '237', 'Banco Bradesco');
INSERT INTO banco (id, codigo, nome) VALUES (3, '341', 'Banco Itaú');