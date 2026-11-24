# Aluno Online API

API REST desenvolvida com Spring Boot para gerenciamento de informações acadêmicas, incluindo alunos, professores e autenticação de usuários.

## Tecnologias utilizadas

- Java 24
- Spring Boot 4.0.3
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- PostgreSQL
- Maven
- Swagger / OpenAPI

## Funcionalidades

- Cadastro e consulta de alunos.
- Cadastro de professores.
- Cadastro de usuários.
- Autenticação de usuários com login e senha.
- Geração de tokens JWT.
- Proteção de rotas por autenticação.
- Documentação dos endpoints com Swagger.

## Autenticação

A API utiliza JWT para autenticar os usuários.

O fluxo de autenticação funciona da seguinte forma:

1. O usuário realiza seu cadastro.
2. Informa login e senha no endpoint de autenticação.
3. A API valida as credenciais.
4. Um token JWT é gerado.
5. O token deve ser enviado no cabeçalho Authorization para acessar as rotas protegidas.

Formato do cabeçalho:

```text
Authorization: Bearer SEU_TOKEN
```

## Endpoints principais

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/cadastros` | Cadastrar usuário |
| POST | `/login` | Realizar autenticação e gerar token |
| GET | `/alunos` | Consultar alunos (autenticado) |
| POST | `/alunos` | Cadastrar aluno (autenticado) |
| POST | `/professores` | Cadastrar professor (autenticado) |

## Configuração do projeto

### Pré-requisitos

- Java 24 ou superior.
- Maven.
- PostgreSQL.

### Banco de dados

Configure as informações de conexão com o PostgreSQL no arquivo `application.properties`.

Crie um banco de dados chamado `aluno_online`.

### Variável de ambiente

Configure a variável de ambiente `JWT_SECRET` com uma chave secreta aleatória.

Não compartilhe nem publique essa chave.

### Executar a aplicação

Execute a classe principal:

`AlunoOnlineApplication.java`

A aplicação será iniciada na porta 8080.

## Documentação da API

Com a aplicação em execução, acesse:

http://localhost:8080/swagger-ui.html

A documentação permite visualizar e testar os endpoints da API.

## Testes realizados

- Cadastro de usuário com sucesso (HTTP 201).
- Autenticação e geração de token JWT (HTTP 200).
- Acesso a rota protegida com token válido (HTTP 200).
- Bloqueio de acesso sem token (HTTP 401).

---

**Projeto acadêmico — Aluno Online.**