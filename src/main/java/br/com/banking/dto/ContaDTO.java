package br.com.banking.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaDTO {
    private Long id;
    private Long bancoId;
    private String conta;
    private String beneficiario;
    private BigDecimal saldo;
}
