package br.com.banking.specification;

import br.com.banking.entity.Transferencia;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TransferenciaSpecification {

    public static Specification<Transferencia> byContaId(Long contaId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("origem").get("id"), contaId),
                        criteriaBuilder.equal(root.get("destino").get("id"), contaId)
                );
    }

    public static Specification<Transferencia> byDataBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("data"), startDate, endDate);
    }
}
