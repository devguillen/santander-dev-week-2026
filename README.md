# DevQuest API - Gamified Portfolio (Nível Avançado)

Bem-vindo(a) ao **DevQuest API**, um projeto revolucionário desenvolvido para o Santander Dev Week 2023. Este projeto é uma evolução ("Nível Avançado") que muda completamente o contexto da aplicação base, transformando o seu portfólio em um "RPG Gamificado".

## 🚀 O Projeto

A ideia é transformar o tradicional currículo PDF em uma experiência API First, focada 100% num Desenvolvedor Herói que sobe de nível conforme completa Quests (projetos) e permite que Recrutadores enviem Propostas diretamente pelo Swagger.

## 🛠 Tecnologias e Padrões (Nível Avançado)

- **Java 17** e **Spring Boot 3**
- **Spring Data JPA** (PostgreSQL / H2)
- **OpenAPI / Swagger** (Documentação dos endpoints)
- **DTOs com Java Records**: Padrão de transferência de dados moderno.
- **Global Exception Handling**: Usando `@RestControllerAdvice` para centralizar as mensagens e códigos de erro e manter as respostas elegantes.

## 🔗 Endpoints de Destaque

- `POST /api/developers` - Crie o seu Perfil/Personagem no banco de dados.
- `POST /api/developers/{id}/quests` - Complete uma Quest (adicione projetos do GitHub e ganhe XP e Level).
- `POST /api/proposals/developer/{id}` - Recrutadores podem usar este endpoint para enviar ofertas de emprego com Range Salarial!

## 🏃‍♂️ Como Rodar (Local e Nuvem)

### Localmente
1. Mantenha o perfil ativo como `dev` em `application.yml` (utiliza o H2 Database em memória).
2. Execute o projeto na raiz:
   ```bash
   ./gradlew bootRun
   ```
3. Abra a documentação Swagger com UI interativa em: `http://localhost:8080/swagger-ui.html`

### Deploy no Railway
1. Conecte o repositório ao seu projeto **Railway**.
2. O framework já suporta leitura do `application-prd.yml` desde que as variáveis de ambiente corretas do PostgreSQL estejam lá (PGHOST, PGDATABASE, PGPORT, PGUSER, PGPASSWORD).
3. Adicione o comando de Start `java -jar build/libs/santander-dev-week-2023-0.0.1-SNAPSHOT.jar`. Prontinho!

## 👩‍💻 Autor

- Feito por meio do desafio [Santander Dev Week 2023](https://github.com/digitalinnovationone/santander-dev-week-2023-api) com auxílio e arquitetura refinada.
