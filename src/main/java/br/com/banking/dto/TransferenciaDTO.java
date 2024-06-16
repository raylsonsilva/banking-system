package br.com.banking.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class TransferenciaDTO {
    private UUID id;
    private LocalDate data;
    private Long origemId;
    private Long destinoId;
    private BigDecimal valor;
}
