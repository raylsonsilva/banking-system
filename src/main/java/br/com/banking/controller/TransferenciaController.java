package br.com.banking.controller;

import br.com.banking.dto.TransferenciaDTO;
import br.com.banking.entity.Transferencia;
import br.com.banking.service.TransferenciaService;
import br.com.banking.specification.TransferenciaSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transferencias")
@Tag(name = "Transferencia", description = "API para gerenciamento de transferências bancárias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    @RolesAllowed("ROLE_TRANSF_LST")
    @Operation(summary = "Listar todas as transferências")
    public ResponseEntity<List<TransferenciaDTO>> getAllTransferencias(
            @RequestParam(required = false) Long contaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        var spec = Specification.where((Specification<Transferencia>) null);

        if (contaId != null) {
            spec = spec.and(TransferenciaSpecification.byContaId(contaId));
        }

        if (startDate != null && endDate != null) {
            spec = spec.and(TransferenciaSpecification.byDataBetween(startDate, endDate));
        }

        List<TransferenciaDTO> transferencias = transferenciaService.findAll(spec);
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed("ROLE_TRANSF_ADD")
    @Operation(summary = "Criar uma nova transferência")
    public ResponseEntity<TransferenciaDTO> createTransferencia(@Valid @RequestBody TransferenciaDTO transferenciaDTO) {
        try {
            TransferenciaDTO newTransferencia = transferenciaService.save(transferenciaDTO);
            return new ResponseEntity<>(newTransferencia, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
