package br.com.banking.repository;

import br.com.banking.entity.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
}
