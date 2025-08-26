<h1> Desafio: Aprenda na Prática Programação Orientada a Objetos</h1>

Disponibilizado com ♥ por [cami-la](https://www.linkedin.com/in/cami-la/ "cami-la").

# Plataforma de Gerenciamento de Bootcamps

## 📖 Sobre o Projeto

Este projeto é uma aplicação de console desenvolvida em Java que simula uma plataforma de gerenciamento de bootcamps educacionais. O sistema foi construído aplicando os quatro pilares da Programação Orientada a Objetos (POO): Abstração, Encapsulamento, Herança e Polimorfismo.

O que começou como um desafio de modelagem de classes evoluiu para um sistema interativo completo, onde o usuário pode criar, gerenciar e interagir com diferentes entidades como Bootcamps, Devs, Cursos e Mentorias através de um menu dinâmico no terminal.

## ✨ Funcionalidades Principais

O sistema oferece uma interface de linha de comando (CLI) rica em funcionalidades, permitindo ao usuário:

*   **Gerenciamento Dinâmico de Bootcamps:**
    *   Criar múltiplos bootcamps, especificando nome e descrição.
    *   Adicionar conteúdos (Cursos e Mentorias) de forma contextual a cada bootcamp.
    *   Listar todos os bootcamps criados, exibindo informações relevantes como número de conteúdos e de devs inscritos.

*   **Gerenciamento Dinâmico de Devs:**
    *   Criar múltiplos devs.
    *   Inscrever devs em bootcamps disponíveis como parte do fluxo de criação.
    *   Acompanhar o progresso individual de cada dev.

*   **Criação de Conteúdos:**
    *   Criar Cursos, definindo título, descrição e carga horária.
    *   Criar Mentorias, definindo título, descrição e data.

*   **Sistema de Progressão e XP:**
    *   Devs podem "progredir" nos conteúdos em que estão inscritos.
    *   Cada conteúdo concluído concede uma quantidade de XP (Pontos de Experiência), que é somada ao total do dev.

*   **Sistema de Ranking:**
    *   Cada bootcamp possui um ranking dinâmico que classifica os devs inscritos com base na sua pontuação total de XP, do maior para o menor.

*   **Geração de Dados para Testes:**
    *   Funcionalidade para criar **Bootcamps Aleatórios**, já preenchidos com uma variedade de cursos e mentorias.
    *   Funcionalidade para criar **Devs Aleatórios**, que são automaticamente inscritos em um bootcamp e progridem um número aleatório de vezes, facilitando a simulação e o teste do sistema.

## 🚀 Como foi Desenvolvido

O desenvolvimento seguiu uma abordagem incremental, partindo de uma base conceitual de POO e evoluindo para uma aplicação interativa:

1.  **Modelagem do Domínio:** Foram criadas as classes principais (`Bootcamp`, `Dev`, `Conteudo`, `Curso`, `Mentoria`) para abstrair as entidades do mundo real, utilizando **Herança** (Curso e Mentoria herdam de Conteudo) e **Encapsulamento** (protegendo os atributos com modificadores de acesso).

2.  **Implementação da Lógica de Negócio:** Foram desenvolvidos os métodos que definem as regras do sistema, como `inscreverBootcamp()`, `progredir()` e `calcularTotalXp()`. O **Polimorfismo** é aplicado na forma como o sistema lida com diferentes tipos de `Conteudo` de maneira uniforme.

3.  **Criação do Menu Interativo:** A classe `Main` foi transformada em um hub de interação com o usuário, utilizando `Scanner` para ler as entradas e `switch-case` para navegar entre as funcionalidades.

4.  **Refatoração e Melhoria de Fluxo:** O menu foi refinado para ser mais intuitivo. Em vez de menus complexos, as ações foram integradas de forma contextual. Por exemplo, ao criar um Dev, o sistema oferece a opção de inscrevê-lo em um bootcamp na mesma etapa.

5.  **Adição de Ferramentas de Teste:** Para facilitar a demonstração e a verificação das funcionalidades (como o ranking), foram adicionadas as opções de geração de dados aleatórios, tornando a aplicação robusta e fácil de testar.

## 🛠️ Tecnologias Utilizadas

*   **Java 11**
*   **Programação Orientada a Objetos (POO)**
*   **IDE IntelliJ**

## Como Executar o Projeto

1.  Clone este repositório em sua máquina local.
2.  Abra o projeto em sua IDE Java de preferência.
3.  Localize e execute o método `main` na classe `Main.java`.
4.  Interaja com o sistema através do menu que aparecerá no console.
