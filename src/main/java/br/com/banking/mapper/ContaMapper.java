package br.com.banking.mapper;

import br.com.banking.dto.ContaDTO;
import br.com.banking.entity.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    @Mapping(source = "banco.id", target = "bancoId")
    ContaDTO toDto(Conta conta);

    @Mapping(source = "bancoId", target = "banco.id")
    Conta toEntity(ContaDTO contaDTO);

    @Mapping(source = "bancoId", target = "banco.id")
    void updateEntityFromDto(ContaDTO contaDTO, @MappingTarget Conta conta);
}
