package br.com.banking.service;

import br.com.banking.dto.TransferenciaDTO;
import br.com.banking.entity.Transferencia;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface TransferenciaService {
    List<TransferenciaDTO> findAll(Specification<Transferencia> spec);
    TransferenciaDTO save(TransferenciaDTO transferenciaDTO);
}
