# 📐 Documentação de Arquitetura — Projeto Spring Boot

Esta documentação descreve a arquitetura adotada no projeto **freela.freelancer**, explicando **responsabilidades de cada camada**, **regras de dependência** e **boas práticas** para manter o código limpo, escalável e profissional.

---

## 🎯 Objetivos da Arquitetura

* Separar responsabilidades de forma clara
* Evitar acoplamento entre regras de negócio e frameworks
* Facilitar manutenção e evolução (monólito → microserviços)
* Permitir testes unitários eficientes
* Padronizar organização do código

---

## 🧱 Visão Geral das Camadas

```
com.freela.freelancer
 ├─ application
 ├─ domain
 ├─ infrastructure
 ├─ presentation
 └─ FreelancerApplication
```

Cada camada possui **responsabilidades bem definidas** e **regras claras de dependência**.

---

## 🧠 Domain (Regra de Negócio)

### Responsabilidade

* Representar o **negócio puro**
* Conter regras, validações e conceitos do domínio

### Pode conter

* Entidades de domínio (model)
* Value Objects
* Exceções de negócio

### Não pode conter

* Anotações Spring
* JPA / Entity
* DTO
* WebClient / HTTP

### Exemplo

```
domain
 ├─ address
 │   ├─ Endereco
 │   ├─ Cep
 │   └─ exception
 └─ bank
```

---

## ⚙️ Application (Casos de Uso)

### Responsabilidade

* Orquestrar o fluxo da aplicação
* Aplicar regras de negócio usando o domínio
* Decidir quando chamar banco ou APIs externas

### Pode conter

* Services (use cases)
* Coordenação entre domain e infrastructure

### Não pode conter

* Controller
* DTO de API
* Entity JPA

### Exemplo

```
application
 └─ address
     └─ CityService
```

📌 **Somente o application pode chamar repository ou clients externos**.

---

## 🧱 Infrastructure (Detalhes Técnicos)

### Responsabilidade

* Tudo que é detalhe técnico ou externo
* Pode ser trocado sem afetar o domínio

### Submódulos comuns

#### 🔧 Config

* CORS
* Swagger
* WebClient

```
infrastructure/config
```

#### 🔐 Security

* SecurityConfig
* JWT Provider
* Filtros

```
infrastructure/security
 ├─ config
 ├─ filter
 └─ provider
```

#### 🗄️ Persistence

* Entity (JPA)
* Repository

```
infrastructure/persistence
 ├─ entity
 └─ repository
```

#### 🌐 Integration

* Integrações externas (ViaCEP, IBGE)

```
infrastructure/integration
 ├─ viacep
 └─ ibge
```

---

## 🌍 Presentation (API / HTTP)

### Responsabilidade

* Expor endpoints REST
* Receber e devolver dados via HTTP
* Traduzir exceções em respostas HTTP

### Pode conter

* Controllers
* DTOs (request/response)
* Response padrão
* Exception Handler

### Organização recomendada

```
presentation
 ├─ address
 │   ├─ controller
 │   └─ dto
 ├─ bank
 ├─ feed
 ├─ workers
 ├─ handler
 └─ dto
     └─ response
         └─ RespostaPadrao
```

📌 **Controller nunca contém regra de negócio**.

---

## 📦 DTO vs Entity vs Domain

| Tipo   | Camada         | Função           |
| ------ | -------------- | ---------------- |
| DTO    | presentation   | Comunicação HTTP |
| Domain | domain         | Regra de negócio |
| Entity | infrastructure | Banco de dados   |

---

## 🧩 Repository

### Responsabilidade

* Acesso ao banco de dados
* CRUD

### Regras

* Usa Entity
* Não contém regra de negócio
* Só é chamado pelo application/service

---

## 🌐 Integrações Externas

### Padrão

* Criar um **Client/Gateway**
* Nunca chamar API externa direto no service

### Exemplo

```
infrastructure/integration/ibge/IbgeClient
```

📌 Client **não valida regra**, apenas consulta.

---

## 🚨 Exceções

### Onde ficam

| Tipo               | Local                                |
| ------------------ | ------------------------------------ |
| Regra de negócio   | domain/exception                     |
| Integração externa | infrastructure/integration/exception |
| Banco              | infrastructure/persistence/exception |
| HTTP               | presentation/handler                 |

📌 Usar `@ControllerAdvice` para mapear exceções → HTTP.

---

## 🧰 Utils e Constants

### Constants

* Valores fixos
* Devem ter contexto

```
infrastructure/constants
presentation/constants
```

### Utils

* Funções puras
* Sem regra de negócio

⚠️ Use com moderação.

---

## 🔁 Regras de Dependência (CRÍTICO)

```
presentation → application → domain
application → infrastructure
domain ❌ depende de ninguém
```

Se essa regra for respeitada, a arquitetura está protegida.

---

## ✅ Conclusão

Esta arquitetura:

* É escalável
* É testável
* É padrão de mercado
* Funciona para monólitos e microserviços

👉 **Se mantida, evita 90% dos problemas arquiteturais em projetos Spring Boot.**

---

📌 *Documento vivo — pode (e deve) evoluir conforme o projeto cresce.*

📌 mais pra frente vcamos migrar para microservices