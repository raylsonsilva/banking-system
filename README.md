# Descrição do Projeto

Este projeto é uma API para gerenciamento de contas bancárias e transferências financeiras. Ele utiliza Spring Boot e Keycloak para autenticação e autorização. A API permite operações CRUD em contas bancárias e a criação de transferências financeiras, com controle de permissões baseado em roles.

# Tecnologias e Ferramentas Utilizadas

- **Java 11**
- **Spring Boot 2.7**
    - Spring Web
    - Spring Data JPA
    - Spring Security
    - Springdoc OpenAPI (Swagger)
- **Keycloak**
- **H2 Database**
- **Flyway**
- **MapStruct**
- **Lombok**
- **Maven**

# Boas Práticas

- **SOLID Principles**: O código segue os princípios SOLID para garantir manutenibilidade e escalabilidade.
- **DTOs**: Uso de Data Transfer Objects (DTOs) para transferência de dados entre camadas.
- **MapStruct**: Utilizado para mapeamento entre entidades e DTOs.
- **Anotações de Segurança**: Utilização de `@RolesAllowed` para controle de acesso baseado em roles diretamente nos endpoints.
- **Swagger**: Documentação da API disponível via Swagger UI.
- **Transações**: Uso de transações para operações críticas de banco de dados.
- **Tratamento de Exceções**: Manipulação centralizada de exceções com `@ControllerAdvice`.
- **Versionamento do Banco de Dados**: Uso do Flyway para versionar as alterações no banco de dados a partir de migrations.

# Passo a passo para executar a aplicação

1. **Clone o Repositório**
    ```bash
    git clone <url-do-repositorio>
    cd <nome-do-diretorio>
    ```

2. **Configure o Keycloak**
    - Configure um servidor Keycloak e crie um realm.
    - Crie um client com o ID `sinple-web` e configure o tipo de acesso como `confidential`.
    - Configure as roles `ROLE_CONTA_LST`, `ROLE_CONTA_ADD`, `ROLE_CONTA_UPD`, `ROLE_CONTA_DEL`, `ROLE_TRANSF_LST`, `ROLE_TRANSF_ADD`.
    - Crie usuários e atribua as roles conforme necessário.

3. **Configurar Variáveis de Ambiente**
    - Configure as variáveis de ambiente conforme necessário (veja a seção "Variáveis de Ambiente" abaixo).

4. **Compile e Execute a Aplicação**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

5. **Acesse a Documentação da API**
    - Abra o navegador e acesse `http://localhost:8080/swagger-ui.html`
    - Use o botão "Authorize" para se autenticar usando o Keycloak.

# Configurações do Projeto

Para facilitar a execução do projeto as configurações de desenvolvimento foram colocadas no `application.properties`.

Vale ressaltar que no ambiente de desenvolvimento real seriam utilizadas variáveis de ambiente e em produção outras tecnologias de cofres de senhas, como Secrets Manager, por exemplo.

# Collection do Postman

Para facilitar o teste da API, você pode importar a coleção do Postman fornecida na pasta `resources` do projeto.

### Importar a Collection no Postman

1. Abra o Postman e clique em "Import".
2. Selecione o arquivo JSON da coleção baixada.
3. Configure as variáveis de ambiente do Postman para corresponder às suas configurações de Keycloak.
4. A coleção do Postman incluirá exemplos de requisições para todos os endpoints da API, incluindo os cabeçalhos de autorização necessários.

# Autor

Este código foi desenvolvido por `Raylson Lima`. Atuo na área de Tecnologia da Informação, há mais de 15 anos, sendo Especialista em análise, modelagem e desenvolvimento de sistemas para arquitetura Web atuando com as principais tecnologias Front-end e Back-end do mercado, tendo participado de projetos em diversos segmentos de negócios (Serviços Educacionais, Financeiros, Planos de Saúde, E-commerce/Marketplace e Agronegócio). Além disso, tenho uma vasta experiência como Professor/Tutor na área de desenvolvimento de sistemas, banco de dados e engenharia de software.