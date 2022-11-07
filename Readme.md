# API RESTfull de Clientes com Spring Boot

Estrutura do Projeto:

Pacotes e Descrição
------ ------------
1. domain:       Camada que contém as entidades da aplicação
2. dto:          Camada que contém os dtos da aplicação
3. repositories: Camada que contém os repositórios com base no Spring Data JPA
4. services:     Camada que detém as regras de negócio e comunicação com a base de dados via repositories
5. resources:    Camada que contém os recursos https expostos na API
6. security:     Camada responsável para toda configuração de segurança
7. config:       Camada que contém as configurações da aplicação
8. mapper:       Camada de mapeamento do pacote dto

------ ------------

Este projeto disponibiliza uma API de Clientes para:

1. Consulta Clientes por código.
2. Consulta a base de Clientes com paginação.
3. Inclusão de Clientes.
4. Alteração de dados de Clientes.
5. Exclusão de Clientes.

A API pode ser configurada para utilizar:
------ ------------

1. Perfil de testes utilizando o banco de dados H2.
2. Perfil de desenvolvimento utilizando o banco de dados MySQL.
------ ----------

A API tem implementada segurança com JWT - JSon Web Token
------ ------------

A API tem implementado o Swagger para documentação.
------ ------------

1. O acesso ao Swagger é através da URL: http://localhost:8081/swagger-ui.html#/

