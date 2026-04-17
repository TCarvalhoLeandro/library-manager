<h1 align="center">📚 Library Management System</h1>

<p align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Interface-Console_CLI-black?style=for-the-badge" alt="CLI">
  <img src="https://img.shields.io/badge/Database-CSV_Files-success?style=for-the-badge" alt="CSV">
  <img src="https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white" alt="Ubuntu">
  <img src="https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white" alt="Git">
<img src="https://img.shields.io/badge/Pattern-Repository-green?style=for-the-badge" alt="Repository Pattern">
  <img src="https://img.shields.io/badge/Architecture-MVC-purple?style=for-the-badge" alt="MVC">
</p>

Sistema de Linha de Comando (CLI) desenvolvido para gerir o catálogo de livros, leitores e os empréstimos de uma biblioteca. O projeto foi construído aplicando conceitos sólidos de **Programação Orientada a Objetos (POO)**, **Arquitetura em Camadas** e **Persistência de Dados em Arquivos de Texto**.

---

## 🛠️ Tecnologias Utilizadas

Neste projeto, o foco foi a construção de uma base arquitetural forte usando apenas a linguagem pura, preparando o terreno para frameworks futuros:

* **Linguagem:** Java 
* **Acesso a Dados:** Leitura e Escrita nativa em Arquivos de Texto (`.csv`)
* **Interface:** Command Line Interface (CLI)
* **Ferramentas:** Git e Eclipse / Spring Tool Suite (STS)
* **Ambiente:** Desenvolvido e testado em ambiente Linux (Ubuntu)

---

## 🏗️ Arquitetura e Padrões de Projeto

 * **MVC / Camadas:** Separação estrita entre Apresentação (`View/Menu`), Lógica de Negócio (`Service`) e Acesso a Dados (`Repository/DAO`).
  * **Repository Pattern:** Abstração das coleções de dados, facilitando a troca da tecnologia de persistência.
  * **Injeção de Dependência:** Baixo acoplamento das classes orquestrado via construtores na classe `Main`.
  * **Fail-Fast:** Tratamento preventivo de erros e regras de negócio.

---

## ✳️ Funcionalidades

- **Gestão de Livros e Leitores:** Operações completas de CRUD (Criar, Ler, Atualizar, Apagar) integradas aos arquivos CSV.
- **Gestão de Empréstimos:** Controle dinâmico de datas (`LocalDate`), status de devolução e atualização automática da disponibilidade dos livros.
- **Regras de Negócio Avançadas:** - Restrição de empréstimos para livros indisponíveis ou leitores com pendências.
  - Prevenção de exclusão de livros que possuem histórico de empréstimo ativo.
  - Lógica de Auto-Incremento para geração segura de IDs a partir da leitura dos arquivos.
- **Tratamento de Exceções:** Criação de exceções personalizadas (`DadosException`, `RuntimeException`) para manter a integridade dos arquivos mesmo em caso de dados vazios ou corrompidos.

---

## ⚙️ Como Executar o Projeto

Como se trata de uma aplicação baseada em terminal (Console), não há hospedagem em nuvem. Para rodar o sistema na sua máquina:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/TCarvalhoLeandro/library-manager.git
   
2. Abra na sua IDE: Importe o projeto no Eclipse, IntelliJ ou VS Code.

3. Execute: Localize a classe principal Main.java e execute o projeto (Run).

4. Navegue: Utilize o terminal integrado da IDE para interagir com o menu principal da biblioteca. Os arquivos .csv serão gerados automaticamente na raiz do projeto.

---

## 💡 Aprendizado



Nesta fase do projeto, implementei uma refatoração estrutural profunda, migrando para uma arquitetura orientada a objetos robusta e escalável. Os principais conceitos aplicados foram:

### 🏗️ Padrões de Projeto (Design Patterns) e Arquitetura
* **Data Access Object (DAO)**: Implementado através de interfaces para as entidades `Livro`, `Leitor` e `Emprestimo`, com implementações concretas (`CSVDAO`) no pacote `.repositories.impl`. 
  * *O que resolveu:* Isolou a lógica de negócio (Services) da complexidade de acesso a dados (leitura e escrita de arquivos CSV).
* **Separação de Conceitos (Separation of Concerns)**: Divisão semântica do projeto em pacotes especialistas (`.ui`, `.resources`, `.services`, `.repositories`). 
  * *O que resolveu:* Garantiu que a interface de usuário (`.ui`) não acesse dados diretamente, e que a persistência (`.repositories`) não contenha regras de negócio, facilitando a navegação e manutenção do código.

### 🧩 Princípios SOLID e Orientação a Objetos
* **Single Responsibility Principle (SRP)**: Aplicado ao garantir que o pacote `.services` seja o único responsável por validar regras de negócio antes de repassar a instrução para o repositório. Cada classe agora tem apenas um motivo para mudar.
* **Dependency Inversion Principle (DIP) e Injeção de Dependência**: Os serviços agora dependem de abstrações (Interfaces DAO) e não de implementações concretas (classes CSV). O controle de qual implementação usar foi invertido (Inversão de Controle).
  * *O que resolveu:* Baixo acoplamento. O sistema está preparado para evoluir sem quebrar o código existente.

### 🧹 Clean Code e Boas Práticas em Java
* **Código Expressivo**: Eliminação de comentários redundantes, delegando a responsabilidade de "explicar" o que o sistema faz para a própria nomenclatura de métodos e variáveis.
* **Exceções Personalizadas**: Criação de semântica rica para tratamento de erros, substituindo falhas genéricas por exceções que representam exatamente o que deu errado no domínio da aplicação.
* **Gerenciamento de Memória com `StringBuilder`**: Substituição da concatenação comum de `Strings` (que gera excesso de objetos descartáveis na memória, pois *Strings* são imutáveis em Java) pelo uso do `StringBuilder`, otimizando a performance em operações dinâmicas de texto.


---

## 💻 Resultado

<img src="images/Captura de tela de 2026-04-17 12-55-08.png"
" width="500">

---

## 📌 Sugestões
Fique a vontade para entrar em contato:

[![LinkedIn](https://img.shields.io/badge/linkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/leandrocarvalho1979)

---

Leandro Carvalho