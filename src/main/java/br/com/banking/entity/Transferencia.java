package br.com.banking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Transferencia {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDate data = LocalDate.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "origem_id", nullable = false)
    private Conta origem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destino_id", nullable = false)
    private Conta destino;

    @DecimalMin(value = "0.01", inclusive = true)
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Conta getOrigem() {
        return origem;
    }

    public void setOrigem(Conta origem) {
        this.origem = origem;
    }

    public Conta getDestino() {
        return destino;
    }

    public void setDestino(Conta destino) {
        this.destino = destino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
