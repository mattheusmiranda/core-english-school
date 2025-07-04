# Core English School

## 📌 Visão Geral
Este projeto segue a **arquitetura hexagonal** para organizar as responsabilidades de forma modular e desacoplada. O objetivo é construir uma aplicação financeira que utiliza **Kotlin** e **Spring Boot** para gerenciar eventos financeiros com Kafka, métricas de observabilidade e um banco de dados em memória para desenvolvimento.

## 🚀 Tecnologias Utilizadas

- **Linguagem:** Kotlin (JDK 21)
- **Framework:** Spring Boot 3.4.5
- **Build System:** Gradle (Kotlin DSL)
- **Banco de Dados:** H2 (com migrations via Flyway)
- **Mensageria:** Apache Kafka
- **Observabilidade:** Micrometer + Prometheus + Grafana
- **Containerização:** Docker

## 📂 Estrutura do Projeto
```
finance-app/
├── build.gradle.kts  # Configuração raiz
├── settings.gradle.kts  # Define os submódulos
├── core/
│   ├── build.gradle.kts
│   ├── src/main/kotlin/com/seuapp/core/
│   └── (entidades, regras de negócio, portas)
├── application/
│   ├── build.gradle.kts
│   ├── src/main/kotlin/com/seuapp/application/
│   └── (casos de uso, serviços)
├── infrastructure/
│   ├── build.gradle.kts
│   ├── src/main/kotlin/com/seuapp/infrastructure/
│   └── (repositórios, adaptação de tecnologia, banco de dados)
└── bootstrap/
│    ├── build.gradle.kts
│    ├── src/main/kotlin/com/seuapp/bootstrap/
│    └── (configuração Spring Boot)
└── entrypoint/
    ├── build.gradle.kts
    ├── src/main/kotlin/com/seuapp/entrypoint/
    └── (APIS, Consumidores e Consumidores, avros)
```

## 🔹 Módulos Explicados

### 1️⃣ **Core** (`core/`)
Este módulo contém:
- **Entidades:** Modelos do domínio financeiro.
- **Regras de negócio:** Lógica central sem dependências externas.
- **Portas (Interfaces):** Definem contratos para os adaptadores externos.

⚠️ **Não contém dependências do Spring!**

---

### 2️⃣ **Application** (`application/`)
Este módulo implementa os **casos de uso** da aplicação e gerencia:
- **Serviços:** Executam a lógica do negócio.
- **Interação com as portas:** Comunicação com o `core/`.

Este módulo depende do `core/`, mas **não depende de infraestrutura**.

---

### 3️⃣ **Infrastructure** (`infrastructure/`)
Este módulo contém as implementações técnicas, como:
- **Repositórios:** Implementações das portas definidas no `core/`.
- **Integração com Kafka:** Publicação e consumo de eventos.
- **Configuração do banco de dados (H2 + Flyway)**.

Este módulo **depende do `application/`**.

---

### 4️⃣ **Bootstrap** (`bootstrap/`)
Este é o **ponto de entrada da aplicação**, contendo:
- **Classe principal do Spring Boot** (`@SpringBootApplication`).
- **Configuração de Beans globais**.
- **Dependência apenas do `infrastructure/`**.

⚠️ **Nenhum outro módulo pode depender de `bootstrap/`!**

---

## 🛠️ Como Rodar o Projeto
1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/finance-app.git
   cd finance-app
   ```
2. Compile e construa o projeto:
   ```sh
   ./gradlew build
   ```
3. Execute o projeto via Spring Boot:
   ```sh
   ./gradlew :bootstrap:bootRun
   ```

---

## 📊 Observabilidade
O projeto coleta métricas usando **Micrometer** e expõe no **Prometheus**:
- Acesse `http://localhost:8080/actuator/prometheus` para visualizar métricas.
- O **Grafana** pode ser usado para monitoramento em tempo real.

---

## 📩 Mensageria com Kafka
Para rodar o Kafka via Docker, use:
```sh
docker-compose up -d kafka zookeeper
```
O projeto publicará e consumirá eventos dos tópicos configurados.

---

## 🎯 Conclusão
Esta estrutura modular permite **baixo acoplamento**, facilitando manutenção e escalabilidade. A arquitetura hexagonal garante que a lógica do domínio seja protegida e que tecnologias externas sejam adaptáveis. 🚀

