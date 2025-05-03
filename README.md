# 🩺 MedWizard - API de Prontuário Médico

Bem-vindo à **API REST de Prontuário Médico** desenvolvida com **Java + Spring Boot**! Essa aplicação faz parte do sistema **MedWizard** e é responsável por gerenciar o registro clínico de pacientes, com segurança, escalabilidade e facilidade de integração com outros módulos como agendamento, prescrições e gestão hospitalar.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security + JWT
- JPA + Hibernate
- Flyway (versionamento do banco de dados)
- MySQL
- Docker + Docker Compose

---

## 📦 Como Executar Localmente

### Pré-requisitos

- Docker e Docker Compose instalados

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/medwizard-api.git
cd medwizard-api

# Suba os serviços
./mvnw clean install
docker-compose up --build
```

🔐 Autenticação e Perfis de Acesso
A API utiliza JWT para autenticação e autorização com base em papéis hierárquicos:

```bash
ADMIN > MEDICO > PACIENTE
```

Endpoints Públicos (sem autenticação)

POST /login – Realiza o login

POST /registrar – Cria um novo usuário

POST /atualizar-token – Atualiza o token JWT

GET /verificar-conta – Verifica uma conta de usuário

Perfis e Permissões

| Rota Base              | Método(s) Permitido(s) | Acesso Requerido  |
| ---------------------- | ---------------------- | ----------------- |
| `/medico`              | Todos                  | `ADMIN`, `MEDICO` |
| `/paciente`            | Todos                  | `ADMIN`, `MEDICO` |
| `/prontuario`          | Todos                  | `MEDICO`          |
| `/adicionar-perfil/**` | `PATCH`                | `ADMIN`           |
| `/reativar-conta/**`   | `PATCH`                | `ADMIN`           |


📄 Endpoints Principais
🔸 Prontuários (/prontuario)

| Método   | Endpoint           | Descrição                        |
| -------- | ------------------ | -------------------------------- |
| `POST`   | `/prontuario`      | Cria um novo prontuário          |
| `GET`    | `/prontuario`      | Lista todos os prontuários       |
| `GET`    | `/prontuario/{id}` | Detalha um prontuário específico |
| `PUT`    | `/prontuario`      | Atualiza um prontuário existente |
| `DELETE` | `/prontuario/{id}` | Remove um prontuário do sistema  |


🛠️ Banco de Dados com Flyway
As migrations SQL são versionadas automaticamente com o Flyway. Toda nova alteração no schema deve ser criada em /src/main/resources/db/migration com o padrão:

```commandline
V1__nome_migration.sql
```
O Flyway roda automaticamente ao iniciar a aplicação.

📦 Docker
A API é containerizada com Docker e configurada via Docker Compose para orquestrar:

API Java

MySQL

Adminer (opcional)

Para subir os containeirs, basta inicializar a aplicação que rodará o docker-compose up automaticamente

🧪 Testes
Os testes unitários e de integração podem ser executados com:

```bash
./mvnw test
```

📫 Contato
Em caso de dúvidas, sugestões ou parcerias, entre em contato:

Davi
👨‍💻 Desenvolvedor Fullstack
📧 pedromb.dev@gmail.com
🔗 LinkedIn - linkedin.com/in/pedro-vinicius-dev/

