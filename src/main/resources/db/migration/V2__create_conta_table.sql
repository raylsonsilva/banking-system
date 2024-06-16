-- Criação da tabela Conta
CREATE TABLE conta (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       banco_id BIGINT NOT NULL,
                       conta VARCHAR(10) NOT NULL,
                       beneficiario VARCHAR(60) NOT NULL,
                       saldo DECIMAL(15,2) NOT NULL DEFAULT 1000.00,
                       CONSTRAINT UK_banco_conta UNIQUE (banco_id, conta),
                       CONSTRAINT FK_banco_conta FOREIGN KEY (banco_id) REFERENCES banco (id)
);