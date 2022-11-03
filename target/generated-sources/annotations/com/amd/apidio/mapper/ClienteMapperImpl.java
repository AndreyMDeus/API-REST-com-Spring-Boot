package com.amd.apidio.mapper;

import com.amd.apidio.domain.Cliente;
import com.amd.apidio.dto.ClienteDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-03T17:47:54-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toModel(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( clienteDTO.getId() );
        cliente.setNome( clienteDTO.getNome() );
        cliente.setSobrenome( clienteDTO.getSobrenome() );
        cliente.setEmail( clienteDTO.getEmail() );

        return cliente;
    }

    @Override
    public ClienteDTO toDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId( cliente.getId() );
        clienteDTO.setNome( cliente.getNome() );
        clienteDTO.setSobrenome( cliente.getSobrenome() );
        clienteDTO.setEmail( cliente.getEmail() );

        return clienteDTO;
    }
}
