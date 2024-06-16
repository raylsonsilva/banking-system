package br.com.banking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"banco_id", "conta"})})
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "banco_id", nullable = false)
    private Banco banco;

    @NotBlank
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String conta;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String beneficiario;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldo = new BigDecimal("1000.00");

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
