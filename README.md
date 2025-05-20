# Medical Scheduling Monorepo

Este repositório contém um projeto de agendamentos médicos, organizado como monorepo utilizando Turborepo e pnpm workspaces. Há duas aplicações:

- **Frontend**: Vue 3 + TypeScript + Vite  
- **Backend**: Spring Boot + Maven  

---

## Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- **Node.js** (>=16) e **pnpm** (verifique com `pnpm -v`)  
- **Java JDK 17+**  
- **Maven** (ou usar o wrapper `mvnw` já incluído)  
- **Git**  
- **PostgreSQL** (>=12)

---

## 1. Clonar o repositório

```bash
git clone https://github.com/DeanVini/Medical-Scheduling

cd Medical-Scheduling
```

---

## 2. Instalar dependências

Na raiz do monorepo, execute:

```bash
pnpm install
```

Isso instalará todas as dependências necessárias para front e back.

---

## 3. Configurar o banco de dados PostgreSQL

1. Acesse o console do PostgreSQL (por exemplo, via `psql`):
   ```sql
   psql -U postgres
   ```
2. Crie o banco e usuário:
   ```sql
   CREATE DATABASE medicaldb;
   CREATE USER medicaluser WITH PASSWORD 'sua_senha';
   GRANT ALL PRIVILEGES ON DATABASE medicaldb TO medicaluser;
   ```
3. No arquivo `apps/backend/src/main/resources/application.properties`, ajuste as credenciais:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/medicaldb
   spring.datasource.username=medicaluser
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```

---

## 4. Rodar o projeto (Unificado com Turborepo)

Na raiz do monorepo, execute:

```bash
pnpm install
turbo dev
```

Isso iniciará em paralelo:
- **Frontend**: Vite em `http://localhost:5173`  
- **Backend**: Spring Boot em `http://localhost:8080`

---

## 5. Outros comandos úteis

- **Build completo**:
  ```bash
  pnpm run build
  ```
- **Testes**:
  ```bash
  pnpm run test
  pnpm run test:backend
  ```
- **Limpar cache Turborepo** (em caso de comportamentos estranhos):
  ```bash
  npx turbo kill
  pnpm store prune
  ```

---

## Observações finais

- Variáveis sensíveis podem ser gerenciadas via variáveis de ambiente ou `.env`, ajustando o `application.properties` para referenciar `SPRING_DATASOURCE_URL`, etc.  
- Para produção, considere Docker e Docker Compose para orquestrar banco e apps.

