-- Criação da tabela Transferencia
CREATE TABLE transferencia (
                               id UUID PRIMARY KEY,
                               data DATE NOT NULL DEFAULT CURRENT_DATE,
                               origem_id BIGINT NOT NULL,
                               destino_id BIGINT NOT NULL,
                               valor DECIMAL(15,2) NOT NULL,
                               CONSTRAINT FK_origem_conta FOREIGN KEY (origem_id) REFERENCES conta (id),
                               CONSTRAINT FK_destino_conta FOREIGN KEY (destino_id) REFERENCES conta (id),
                               CONSTRAINT CHK_valor_positivo CHECK (valor > 0)
);