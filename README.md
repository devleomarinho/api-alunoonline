# Aluno Online API

## Descrição

O **Aluno Online** é uma API RESTful desenvolvida em Java usando o framework **Spring Boot**, que segue o padrão arquitetural MVC (Model-View-Controller), como projeto prático da disciplina **Tecnologias para Backend**, do prof. Kelson Almeida. O objetivo do projeto é criar um sistema de secretaria de uma instituição de ensino, capaz de gerenciar informações relacionadas a alunos, professores, matrículas e disciplinas de forma organizada e eficiente, com persistência de dados em um banco de dados PostgreSQL.

## Estrutura do Projeto

O projeto está organizado nas seguintes pastas:

- **config**: Contém a classe `SwaggerConfig`, com as configurações do Swagger, para documentação e teste da API.
- **model**: Contém as entidades do sistema.
  - `Aluno`
  - `Professor`
  - `MatriculaAluno`
  - `Disciplina`
    
- **controller**: Contém as classes controladoras responsáveis por receber requisições HTTP e direcioná-las para as respectivas camadas de serviço:
  - 'AlunoController'
  - 'DisciplinaController'
  - 'MatriculaAlunoController'
  - 'ProfessorController'

- **service**: Contém as regras de negócio e processamento dos dados.
  - 'AlunoService'
  - 'DisciplinaService'
  - 'MatriculaAlunoService'
  - 'ProfessorService'

- **repository**: Contém interfaces que estendem o `JpaRepository` para interagir com o banco de dados.
  - 'AlunoRepository'
  - 'DisciplinaRepository'
  - 'MatriculaAlunoRepository'
  - 'ProfessorRepository' 

- **dtos**: Contém classes de transferência de dados (Data Transfer Objects) para abstrair as entidades do banco de dados e transferir apenas os dados necessários em uma requisição específica.
  - 'AtualizaNotasRequest'
  - 'DisciplinasAlunoResponse'
  - 'HistoricoAlunoResponse'
    
- **enums**: Inclui a classe `MatriculaAlunoStatusEnum`, que define os estados possíveis de uma matrícula:
  - `MATRICULADO`
  - `APROVADO`
  - `REPROVADO`
  - `TRANCADO`

![estrutura_de_pastas](https://github.com/user-attachments/assets/41bbeb19-a372-45eb-af2b-e95119a60b43)


## Funcionalidades

- Alunos: Cadastro, atualização, exclusão e consulta de informações de alunos.
  
  ![image](https://github.com/user-attachments/assets/682cce2a-a5c2-4a51-b883-c0f42496c89f)

- Professores: Cadastro, atualização, exclusão e consulta de informações de professores.

  ![image](https://github.com/user-attachments/assets/73684f03-2ba2-467b-b74f-c591c93ed93e)

- Disciplinas: Cadastro, atualização, exclusão e consulta de informações de disciplinas.

  ![image](https://github.com/user-attachments/assets/17e48174-1d2c-499e-9b94-bc6e5604b6ac)

- Matrículas: Gestão de status de matrícula em disciplinas e sua relação com alunos e professores. Permite trancar uma disciplina, atualizar notas e emitir histórico dos alunos matriculados.

  ![image](https://github.com/user-attachments/assets/1dd8dd5c-33bf-4f8e-a5aa-26f6cb0b0d9a)


## Tecnologias e Dependências Utilizadas

### Linguagem
- **Java 17**: Linguagem principal para desenvolvimento do projeto.
- **Maven**: Gerenciador de dependências

### Frameworks e Bibliotecas
- **Spring Boot**:
  - **Spring Web**: Permite criar APIs RESTful e gerenciar requisições HTTP.
  - **Spring Data JPA**: Facilita a integração com o banco de dados utilizando o padrão ORM.
- **Lombok**: Reduz o código boilerplate, gerando automaticamente getters, setters, construtores e outros métodos padrão.
- **PostgreSQL Driver**: Fornece suporte para conexão com o banco de dados PostgreSQL.
- **Swagger**:
  - Facilita a documentação e o teste interativo das rotas da API através de uma interface web.

### Testes de requisições
- **Insomnia**
  
## Banco de Dados

A API utiliza o **PostgreSQL** como banco de dados para armazenar informações de alunos, professores, matrículas e disciplinas. O acesso ao banco é realizado por meio do Spring Data JPA.


![image](https://github.com/user-attachments/assets/9b32b27f-3580-4255-9245-88aa242e6720)


## Como Executar

1. Certifique-se de ter o **Java 17** e o **PostgreSQL** instalados na sua máquina.
2. Clone este repositório.
3. Configure o `application.properties` com as credenciais do seu banco de dados PostgreSQL.
4. Execute o projeto utilizando sua IDE ou linha de comando:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Acesse a documentação da API no navegador através do Swagger:
    ```bash
    http://localhost:8080/swagger-ui.html
    ```

## Exemplos de testes no Insomnia:


![image](https://github.com/user-attachments/assets/2dd24357-b49a-4f44-83e6-4b2b1b95235b)

![image](https://github.com/user-attachments/assets/b6f19ad6-a29d-412b-b0e8-b15659f40ea6)

![image](https://github.com/user-attachments/assets/b0b71837-28d4-4bb8-8c9f-dbed66fbc90e)

![image](https://github.com/user-attachments/assets/a659b82c-e0bb-442f-b29d-3e133d766d2b)




