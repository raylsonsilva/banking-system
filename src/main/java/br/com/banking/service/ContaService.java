package br.com.banking.service;

import br.com.banking.dto.ContaDTO;
import br.com.banking.entity.Conta;

import java.util.List;

public interface ContaService {
    List<ContaDTO> findAll();
    Conta findByIdEntity(Long id);
    ContaDTO save(ContaDTO contaDTO);
    Conta save(Conta conta);
    ContaDTO update(Long id, ContaDTO contaDTO);
    void deleteById(Long id);
}