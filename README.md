<h1 align="center">Library Management System 📚</h1>

<p align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Interface-Console_CLI-black?style=for-the-badge" alt="CLI">
  <img src="https://img.shields.io/badge/Database-CSV_Files-success?style=for-the-badge" alt="CSV">
  <img src="https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white" alt="Ubuntu">
  <img src="https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white" alt="Git">
  <img src="https://img.shields.io/badge/Pattern-Repository-blue?style=for-the-badge" alt="Repository Pattern">
  <img src="https://img.shields.io/badge/Architecture-MVC-purple?style=for-the-badge" alt="MVC">
</p>

Sistema de Linha de Comando (CLI) desenvolvido para gerir o catálogo de livros, leitores e os empréstimos de uma biblioteca. O projeto foi construído aplicando conceitos sólidos de **Programação Orientada a Objetos (POO)**, **Arquitetura em Camadas** e **Persistência de Dados em Arquivos de Texto**.

## 🛠️ Tecnologias e Padrões Utilizados

Neste projeto, o foco foi a construção de uma base arquitetural forte usando apenas a linguagem pura, preparando o terreno para frameworks futuros:

* **Linguagem:** Java 
* **Acesso a Dados:** Leitura e Escrita nativa em Arquivos de Texto (`.csv`)
* **Interface:** Command Line Interface (CLI)
* **Padrões de Projeto & Arquitetura:**
  * **MVC / Camadas:** Separação estrita entre Apresentação (`View/Menu`), Lógica de Negócio (`Service`) e Acesso a Dados (`Repository/DAO`).
  * **Repository Pattern:** Abstração das coleções de dados, facilitando a troca da tecnologia de persistência.
  * **Injeção de Dependência:** Baixo acoplamento das classes orquestrado via construtores na classe `Main`.
  * **Fail-Fast:** Tratamento preventivo de erros e regras de negócio.
* **Ferramentas:** Git e Eclipse / Spring Tool Suite (STS)
* **Ambiente:** Desenvolvido e testado em ambiente Linux (Ubuntu)

## ⚙️ Funcionalidades

- [x] **Gestão de Livros e Leitores:** Operações completas de CRUD (Criar, Ler, Atualizar, Apagar) integradas aos arquivos CSV.
- [x] **Gestão de Empréstimos:** Controle dinâmico de datas (`LocalDate`), status de devolução e atualização automática da disponibilidade dos livros.
- [x] **Regras de Negócio Avançadas:** - Restrição de empréstimos para livros indisponíveis ou leitores com pendências.
  - Prevenção de exclusão de livros que possuem histórico de empréstimo ativo.
  - Lógica de Auto-Incremento para geração segura de IDs a partir da leitura dos arquivos.
- [x] **Tratamento de Exceções:** Criação de exceções personalizadas (`DadosException`, `RuntimeException`) para manter a integridade dos arquivos mesmo em caso de dados vazios ou corrompidos.

## 🚀 Como Executar o Projeto

Como se trata de uma aplicação baseada em terminal (Console), não há hospedagem em nuvem. Para rodar o sistema na sua máquina:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/TCarvalhoLeandro/library-manager.git
   
2. Abra na sua IDE: Importe o projeto no Eclipse, IntelliJ ou VS Code.

3. Execute: Localize a classe principal Main.java e execute o projeto (Run).

4. Navegue: Utilize o terminal integrado da IDE para interagir com o menu principal da biblioteca. Os arquivos .csv serão gerados automaticamente na raiz do projeto.

👨‍💻 Autor
Leandro Carvalho


[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/leandrocarvalho1979)