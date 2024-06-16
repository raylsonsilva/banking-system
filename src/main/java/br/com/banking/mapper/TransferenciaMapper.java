package br.com.banking.mapper;

import br.com.banking.dto.TransferenciaDTO;
import br.com.banking.entity.Transferencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {

    @Mapping(source = "origem.id", target = "origemId")
    @Mapping(source = "destino.id", target = "destinoId")
    TransferenciaDTO toDto(Transferencia transferencia);

    @Mapping(source = "origemId", target = "origem.id")
    @Mapping(source = "destinoId", target = "destino.id")
    Transferencia toEntity(TransferenciaDTO transferenciaDTO);
}