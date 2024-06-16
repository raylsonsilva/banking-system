package br.com.banking.service.impl;

import br.com.banking.dto.TransferenciaDTO;
import br.com.banking.entity.Conta;
import br.com.banking.entity.Transferencia;
import br.com.banking.mapper.TransferenciaMapper;
import br.com.banking.repository.TransferenciaRepository;
import br.com.banking.service.ContaService;
import br.com.banking.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaService contaService;
    private final TransferenciaMapper transferenciaMapper;

    @Autowired
    public TransferenciaServiceImpl(TransferenciaRepository transferenciaRepository, ContaService contaService, TransferenciaMapper transferenciaMapper) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaService = contaService;
        this.transferenciaMapper = transferenciaMapper;
    }

    @Override
    public List<TransferenciaDTO> findAll(Specification<Transferencia> spec) {
        return transferenciaRepository.findAll(spec)
                .stream()
                .map(transferenciaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransferenciaDTO save(TransferenciaDTO transferenciaDTO) {
        Transferencia transferencia = transferenciaMapper.toEntity(transferenciaDTO);

        Conta contaOrigem = contaService.findByIdEntity(transferencia.getOrigem().getId());
        Conta contaDestino = contaService.findByIdEntity(transferencia.getDestino().getId());

        if (contaOrigem.getId().equals(contaDestino.getId())) {
            throw new IllegalArgumentException("Conta de origem e destino não podem ser iguais");
        }

        if (contaOrigem.getSaldo().compareTo(transferencia.getValor()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente na conta de origem");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(transferencia.getValor()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(transferencia.getValor()));

        contaService.save(contaOrigem);
        contaService.save(contaDestino);

        Transferencia savedTransferencia = transferenciaRepository.save(transferencia);
        return transferenciaMapper.toDto(savedTransferencia);
    }
}