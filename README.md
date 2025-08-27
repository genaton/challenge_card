# 💳 Projeto: Sistema de Gestão de Cartões Bancários
Este projeto foi desenvolvido como parte do **Tech Challenge - Fase 2** da Pós-Tech FIAP. A proposta consiste em criar uma aplicação Java RESTful para gestão de clientes, produtos de cartões e contratações. O foco está em aplicar conceitos de APIs, persistência com banco de dados, orientação a objetos e boas práticas de arquitetura.

---

## 📦 Instruções de instalação e execução da aplicação 

1. Clone o repositório:
   ```bash
   git clone https://github.com/genaton/challenge_card
   cd seu-repositorio

2. Instalar um servidor MySQL

3. As configurações de conexão com o banco de dados, estão no arquivo application.properties deste projeto: challenge_card > src > main > resources

4. No MySQL, executar o comando de criação da base de dados: Create database challenge_api

5. Abra o projeto e execute:

   ./mvnw spring-boot:run

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

## Link para a documentação no Swagger

- Acesse a documentação da API:
👉 http://localhost:8080/swagger-ui/index.html
