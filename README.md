# 💳 Projeto: Sistema de Gestão de Cartões Bancários
Este projeto foi desenvolvido como parte do **Tech Challenge - Fase 2** da Pós-Tech FIAP. A proposta consiste em criar uma aplicação Java RESTful para gestão de clientes, produtos de cartões e contratações. O foco está em aplicar conceitos de APIs, persistência com banco de dados, orientação a objetos e boas práticas de arquitetura.

---

## 🚀 Funcionalidades

### 👤 Gestão de Clientes

- Cadastro de cliente com nome, CPF, e-mail e data de nascimento
- Atualização e exclusão de dados
- Validações com Bean Validation (`@NotBlank`, `@Email`, `@Past`, etc.)
- Documentação clara via Swagger/OpenAPI

### 💳 Gestão de Cartões

- Cadastro de produto de cartão com tipo (crédito/débito), bandeira e anuidade
- Atualização e exclusão
- Paginação e filtro por cartões ativos

### 📌 Contratações

- Associação entre clientes e cartões
- Registro da data de contratação
- Status: ativo ou cancelado
- Visualização de contratos por cliente

---

## 🛠️ Tecnologias Utilizadas

- **Java 24**
- **Spring Boot 3+**
- **Spring Data JPA**
- **Hibernate Validator**
- **MySQL** como banco relacional
- **Flyway** para versionamento de banco
- **Swagger** (SpringDoc OpenAPI) para documentação

---

## 📚 Arquitetura

O projeto segue a estrutura tradicional de camadas:


src/ ├── controller         --> Endpoints REST ├── service            --> Regras de negócio ├── repository         --> Interfaces JPA ├── model              --> Entidades JPA ├── dto                --> Records para entrada e saída └── config             --> Configurações (Swagger, CORS, etc.)

Também foram utilizados Design Patterns como:

- **Repository Pattern**
- **DTO Mapping**
- **Service Layer**
- **Validation Strategy**

---

## 📦 Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/genaton/challenge_card
   cd seu-repositorio

2. Configure o banco de dados no application.properties
3. Execute com:

./mvnw spring-boot:run

- Acesse a documentação da API:
👉 http://localhost:8080/swagger-ui.html

📂 Estrutura do Banco de Dados
O modelo ER contém:
- Tabela clientes
- Tabela cartoes
- Tabela contratacoes
- Relacionamento 1:1 entre clientes e contratacoes
- Relacionamento 1:1 entre cartoes e contratacoes

O modelo pode ser acessado em (vide readme.md do repositório):
https://github.com/genaton/pos_thec_fiap_challenge_db_model

📽️ Apresentação em vídeo
A demonstração do funcionamento está disponível aqui:
🎥 Link para vídeo no YouTube ou Drive


🤝 Contribuidores

- Carla Aparecida Dutra
- Elton Fabiano
- Genaton Alex
- Moises Salgado
- Renan Paschoalotti

📄 Licença
??

---






