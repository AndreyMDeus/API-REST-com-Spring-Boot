package com.amd.apidio.mapper;

import com.amd.apidio.domain.Cliente;
import com.amd.apidio.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toModel(ClienteDTO clienteDTO);

    ClienteDTO toDTO(Cliente cliente);

}
