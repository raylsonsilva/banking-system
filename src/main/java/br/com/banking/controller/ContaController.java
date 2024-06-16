package br.com.banking.controller;

import br.com.banking.dto.ContaDTO;
import br.com.banking.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contas")
@Tag(name = "Conta", description = "API para gerenciamento de contas bancárias")
public class ContaController {

    private final ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    @RolesAllowed("ROLE_CONTA_LST")
    @Operation(summary = "Listar todas as contas")
    public ResponseEntity<List<ContaDTO>> getAllContas() {
        List<ContaDTO> contas = contaService.findAll();
        return new ResponseEntity<>(contas, HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed("ROLE_CONTA_ADD")
    @Operation(summary = "Criar uma nova conta")
    public ResponseEntity<ContaDTO> createConta(@Valid @RequestBody ContaDTO contaDTO) {
        ContaDTO newConta = contaService.save(contaDTO);
        return new ResponseEntity<>(newConta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed("ROLE_CONTA_UPD")
    @Operation(summary = "Atualizar uma conta existente")
    public ResponseEntity<ContaDTO> updateConta(@PathVariable Long id, @Valid @RequestBody ContaDTO contaDTO) {
        try {
            ContaDTO updatedConta = contaService.update(id, contaDTO);
            return new ResponseEntity<>(updatedConta, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_CONTA_DEL")
    @Operation(summary = "Deletar uma conta por ID")
    public ResponseEntity<Void> deleteConta(@PathVariable Long id) {
        try {
            contaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}