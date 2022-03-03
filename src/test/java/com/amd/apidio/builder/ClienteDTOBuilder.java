package com.amd.apidio.builder;

import com.amd.apidio.domain.Cliente;
import com.amd.apidio.domain.enums.TipoCliente;
import com.amd.apidio.dto.ClienteDTO;
import lombok.Builder;

@Builder
public class ClienteDTOBuilder {

    @Builder.Default
    private int id = 1;

    @Builder.Default
    private String nome = "Test Resource";

    @Builder.Default
    private String sobrenome = "Resource";

    @Builder.Default
    private String email = "resource@email.com";

    @Builder.Default
    private String cpfOuCnpj = "12345678911";

    @Builder.Default
    private TipoCliente tipo = TipoCliente.PESSOAFISICA;

    public ClienteDTO toClienteDTO() {
        Cliente cli = new Cliente(id,nome,sobrenome,email,cpfOuCnpj,tipo);
        return new ClienteDTO(cli);
    }
}
