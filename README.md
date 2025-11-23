# Módulo 1 PMP

Módulo 1 do Projeto de PMP para entrega do Projeto Final, nele foi atendido os seguintes requisitos:

```
Módulo 1
- API REST utilizando Spring Boot (versão 3+)
- CRUD de Pessoa (Criar, Ler, Atualizar e Deletar) com os seguintes critérios:

  * Retorno paginado, exibindo 10 itens por página
  * Apenas pessoas com atributo ativo = true são retornadas
  * Banco de dados de escolha do desenvolvedor com tabela padrão:

    ID | NOME | DT_NASCIMENTO | ATIVO

- Logs da aplicação enviados para o Graylog
- Docker-compose com todas as imagens necessárias (banco de dados, Graylog, aplicação, etc.)

```

### Observação
* **Para o 1° módulo, temos apenas esta aplicação. Que irá ser integrada com os próximos módulos.**

---

---

## 📘 Estrutura do Projeto

```
📂 modulo1/
├── 📁 .github                             ← Workflows e CI/CD
├── 📁 .idea                               ← Configurações da IDE
├── 📁 domain                              ← Módulo de domínio Java puro
│   ├── entity/                            ← Entidades do domínio (Pessoa)
│   ├── exception/                         ← Exceções customizadas
│   ├── repository/                        ← Interfaces de persistência
│   └── service/                           ← Regras de negócio
├── 📁 springframework                      ← Módulo Spring Boot
│   ├── configuration/                     ← Configurações e Beans
│   ├── controller/                        ← Controllers REST
│   │   ├── adapter/                        ← Adaptadores DTO ↔ Entidade
│   │   ├── advice/                         ← Tratamento global de exceções
│   │   ├── dto/                            ← DTOs de Request e Response
│   │   └── logging/                        ← Filter de CorrelationId
│   ├── repository/                         ← Implementações da persistência
│   │   ├── adapter/                        ← Conversão Entity ↔ ORM
│   │   ├── client/                         ← Repositório MongoDB
│   │   └── orm/                            ← Mapeamento ORM (MongoDB)
│   └── PessoaApplication.java             ← Classe principal Spring Boot
├── 📁 src/main/resources                   ← Configurações e logs
│   ├── application.properties
│   └── logback-spring.xml
├── 📁 .commands                            ← Scripts para automatizar build/execução
├── 📄 docker-compose.yml                   ← Configuração dos containers
├── 📄 Dockerfile                           ← Build da imagem da aplicação
├── 📄 pom.xml                              ← Build Maven
├── 📄 README.md                            ← Este arquivo
├── 📄 .gitignore
└── 📄 .gitattributes
````
## 🧩 Tecnologias Utilizadas

- **Spring Boot** → Framework Back-End
- **Java** → Linguagem de programação
- **Maven** → Build
- **Docker** → Containers e virtualização
- **MongoDB** → Persistência de dados
- **Redis** → Cache
- **OpenSearch** -> Armazenamento e busca de logs
- **Graylog** → Central de Logs
- **SonarQube** → Qualidade do Código
- **Github Actions** → CI/CD automatizado

---

## ✅ Qualidade de Código (SonarQube)

> A Qualidade de Código do Projeto é Analisada através do SonarQube, verifique os badges a seguir que apresentam as métricas obtidas no projeto!

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=PMP-Projects_modulo1-PMP&metric=bugs)](https://sonarcloud.io/summary/new_code?id=PMP-Projects_modulo1-PMP)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=PMP-Projects_modulo1-PMP&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=PMP-Projects_modulo1-PMP)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=PMP-Projects_modulo1-PMP&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=PMP-Projects_modulo1-PMP)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=PMP-Projects_modulo1-PMP&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=PMP-Projects_modulo1-PMP)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=PMP-Projects_modulo1-PMP&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=PMP-Projects_modulo1-PMP)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=PMP-Projects_modulo1-PMP&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=PMP-Projects_modulo1-PMP)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=PMP-Projects_modulo1-PMP&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=PMP-Projects_modulo1-PMP)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=PMP-Projects_modulo1-PMP&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=PMP-Projects_modulo1-PMP)

---

## 📦 Instalação e Configuração do Ambiente

### 1️⃣ Clone o projeto na sua máquina e baixe as dependências:
```bash
# Clonar repositório
git clone https://github.com/PMP-Projects/modulo1-PMP.git

# Acesse a pasta do projeto
cd modulo1
````

### 2️⃣ Suba os Containers e Rode a Aplicação
```bash
# Inicie os containers (MongoDB, Redis, OpenSearch, Graylog), juntamente com o Dockerfile da aplicação
docker compose up -d --build
```

#### Serviços do Docker Compose

Caso queira acessar o gerenciamento de logs ou a base de dados do MongoDB, você pode utilizar esses acessos
- Graylog Web UI: http://localhost:9000 (usuário: admin, senha: admin)
- MongoDB: localhost:27017


## 📦 Esteira CI/CD com Github Actions

A esteira CI/CD deste projeto é automatizada via Github Actions.

###  Etapas da Esteira:
1️⃣ Verificação de **Vulnerabilidades** com o **Trivy**

2️⃣ Análise de qualidade de código com **Sonar Cloud**

---
## Endpoints

| Método   | Endpoint | Descrição                                                         |
| -------- | -------- | ----------------------------------------------------------------- |
| `POST`   | `/`      | Criar uma nova pessoa                                             |
| `GET`    | `/{id}`  | Buscar pessoa pelo ID                                             |
| `GET`    | `/`      | Listar todas as pessoas ativas (paginação de 10 itens por página) |
| `PUT`    | `/{id}`  | Atualizar os dados de uma pessoa pelo ID                          |
| `DELETE` | `/{id}`  | Desativar (deletar logicamente) uma pessoa pelo ID                |

### Exemplos de uso com cURL

* Obs.: cURLs exportados do Insomnia

#### Criar Pessoa

```bash 
curl --request POST \
  --url http://localhost:8081/modulo1/api/v1/pessoa \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpdXNlcyIsImlhdCI6MTc2Mzg1NDExMCwicm9sZXMiOlsiUk9MRV9VU0VSIl19.WUJzlp_OkKp2-uaI-XuRNqS3fIs0L2fHVualEvbTkXA' \
  --header 'Content-Type: application/json' \
  --data '{
  "nome": "Julio Neves",
  "dataNascimento": "2000-10-10"
}
'
````

#### Buscar pessoa por ID

```bash 
curl --request GET \
  --url http://localhost:8081/modulo1/api/v1/pessoa/ea0a91ab-bcd4-4626-aa5d-44b8f5d4d9f5 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpdXNlcyIsImlhdCI6MTc2Mzg1NDExMCwicm9sZXMiOlsiUk9MRV9VU0VSIl19.WUJzlp_OkKp2-uaI-XuRNqS3fIs0L2fHVualEvbTkXA' \
  --header 'User-Agent: insomnia/11.6.2'
````

#### Listar pessoas ativas
```bash 
curl --request GET \
  --url http://localhost:8081/modulo1/api/v1/pessoa \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpdXNlcyIsImlhdCI6MTc2Mzg1NDExMCwicm9sZXMiOlsiUk9MRV9VU0VSIl19.WUJzlp_OkKp2-uaI-XuRNqS3fIs0L2fHVualEvbTkXA' \
  --header 'User-Agent: insomnia/11.6.2'
````

#### Atualizar pessoa
```bash 
curl --request PUT \
  --url http://localhost:8081/modulo1/api/v1/pessoa/ea0a91ab-bcd4-4626-aa5d-44b8f5d4d9f5 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpdXNlcyIsImlhdCI6MTc2Mzg1NDExMCwicm9sZXMiOlsiUk9MRV9VU0VSIl19.WUJzlp_OkKp2-uaI-XuRNqS3fIs0L2fHVualEvbTkXA' \
  --header 'Content-Type: application/json' \
  --data '{
  "nome": "Juliana Oliveira",
  "dataNascimento": "2000-10-10"
}
'
````
#### Deletar pessoa
````bash
curl --request DELETE \
  --url http://localhost:8081/modulo1/api/v1/pessoa/ea0a91ab-bcd4-4626-aa5d-44b8f5d4d9f5 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpdXNlcyIsImlhdCI6MTc2Mzg1NDExMCwicm9sZXMiOlsiUk9MRV9VU0VSIl19.WUJzlp_OkKp2-uaI-XuRNqS3fIs0L2fHVualEvbTkXA'
  ````

## ✍️ Autor do Projeto

<div align="center">

| [<img src="https://avatars.githubusercontent.com/u/99426563" width=115><br><sub>Júlio Neves</sub>](https://github.com/juliosn)
| :---: |

</div>

---
