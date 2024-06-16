package br.com.banking.service.impl;

import br.com.banking.dto.ContaDTO;
import br.com.banking.entity.Conta;
import br.com.banking.mapper.ContaMapper;
import br.com.banking.repository.ContaRepository;
import br.com.banking.repository.TransferenciaRepository;
import br.com.banking.service.ContaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;
    private final TransferenciaRepository transferenciaRepository;
    private final ContaMapper contaMapper;

    @Autowired
    public ContaServiceImpl(ContaRepository contaRepository, TransferenciaRepository transferenciaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.transferenciaRepository = transferenciaRepository;
        this.contaMapper = contaMapper;
    }

    @Override
    public List<ContaDTO> findAll() {
        return contaRepository.findAll()
                .stream()
                .map(contaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Conta findByIdEntity(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conta not found with id " + id));
    }

    @Override
    public ContaDTO save(ContaDTO contaDTO) {
        Conta conta = contaMapper.toEntity(contaDTO);
        Conta savedConta = contaRepository.save(conta);
        return contaMapper.toDto(savedConta);
    }

    @Override
    public Conta save(Conta conta) {
        return contaRepository.save(conta);
    }

    @Override
    public ContaDTO update(Long id, ContaDTO contaDTO) {
        Conta existingConta = contaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conta not found with id " + id));
        contaMapper.updateEntityFromDto(contaDTO, existingConta);
        Conta updatedConta = contaRepository.save(existingConta);
        return contaMapper.toDto(updatedConta);
    }

    @Override
    public void deleteById(Long id) {

        contaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

        boolean hasTransfers = transferenciaRepository.existsByOrigemIdOrDestinoId(id, id);

        if (hasTransfers) {
            throw new IllegalArgumentException("Conta não pode ser removida porque tem transferências associadas");
        }

        contaRepository.deleteById(id);
    }
}