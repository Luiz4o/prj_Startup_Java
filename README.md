# Startup Auto Fácil Backend

Projeto de backend Java que alimenta a plataforma Auto Fácil, uma solução desenvolvida para facilitar o gerenciamento e a compra de peças automotivas.

Este backend é responsável pela autenticação, cadastro de peças, controle de estoque, gerenciamento de carrinho e armazenamento de imagens de produtos via **AWS S3**. Ele expõe uma API REST que é consumida pelo front-end e por outros serviços externos (como o chatbot em Python que auxilia os compradores dentro da plataforma).

## 🚀 Funcionalidades Implementadas

O backend conta com os seguintes módulos e fluxos principais:

### Autenticação de Usuário
* Login e validação de credenciais.
* Geração e controle de sessão via API.

### Gestão de Peças e Estoque
* Cadastro de novas peças.
* Atualização e consulta de estoque.
* Armazenamento de imagens e arquivos no **AWS S3**.

### Fluxo de Compra
* Criação e manipulação de carrinhos de compras dos usuários.
* Persistência e consulta dos itens do carrinho.

### Integração com Serviços da Plataforma
* Consumo via API REST pelo front-end.
* Comunicação com o serviço de chatbot (Python), que auxilia o usuário durante a compra.

## 🛠️ Tecnologias Utilizadas

### Core do Backend
* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Banco de Dados:** PostgreSQL
* **Execução:** Docker (hospedado no Render)

### Serviços Externos
* **AWS S3:** Armazenamento de imagens e arquivos dos produtos.

### Infra
* Backend hospedado no **Render**.
* Banco PostgreSQL também provisionado no **Render**.

## 📁 Estrutura Macro do Projeto

Estruturas principais (Spring Boot padrão):

```
src/main/java
│ 
├── controllers (Recebem requisições da API, expõem endpoints REST) 
│ 
├── services (Regras de negócio da aplicação) 
│ 
├── repositories (Interfaces de persistência JPA / acesso ao PostgreSQL) 
│ 
├── configs (Configurações de segurança, beans e integrações) 
│ 
└── models (Entidades e DTOs)
```

## ⚙️ Configuração da API (Local)

Para rodar este backend localmente, é necessário:

1.  Subir um serviço PostgreSQL local.
2.  Configurar o `application.properties` com a conexão padrão.
3.  Configurar variáveis de ambiente contendo as chaves do S3 (e demais secrets do projeto).

Depois disso, iniciar com:

```bash
./mvnw spring-boot:run

