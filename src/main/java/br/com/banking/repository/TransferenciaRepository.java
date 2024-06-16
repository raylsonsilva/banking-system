package br.com.banking.repository;

import br.com.banking.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TransferenciaRepository extends JpaRepository<Transferencia, UUID>, JpaSpecificationExecutor<Transferencia> {

    boolean existsByOrigemIdOrDestinoId(Long origemId, Long destinoId);

}
