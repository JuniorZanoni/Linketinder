# Linketinder ZG

Projeto desenvolvido para conclusão do módulo de Groovy do Acelera ZG.

Linketinder ZG é uma aplicação desenvolvida em Groovy com objetivo de desenvolvedores e empresas poderem se encontrar.
Candidatos e empresas podem dar like um no outro, caso o like seja mútuo, é gerado um match, que demonstra que ambas tem
interesse.

## Funcionalidades do projeto

- `Cadastrar candidato`: Cadastrar candidato com os campos, nome, e-mail, estado, cep, cpf, idade, descrição e skills.
- `Cadastrar empresa`: Cadastrar candidato com os campos, nome, e-mail, pais, estado, cep, cnpj, descrição e skills.
- `Listar todos candidatos`: Exibe todos candidatos com todas informações.
- `Listar todas empresas`: Exibe todos empresas com todas informações.
- `Curtir`: Exibe candidatos ou empresas, para que o usúario possa curtir.
- `Ver matches`: Caso dois usúarios se curtam, é dado match e pode ser exibida uma lista com os matches.

<p align="center">
    <img src="https://uploaddeimagens.com.br/images/004/312/321/original/Screenshot_from_2023-01-25_15-36-38.png" />
</p>

## Funcionalidades futuras

- `Persistência de dados em banco SQL`: Todos dados serão persistidos em um banco PostgreSQL, o arquivo .sql e DER podem ser encontrados [aqui](https://github.com/JuniorZanoni/Linketinder/tree/main/banco-de-dados).
- `Match`: No banco de dados, existe uma tabela chamada "matchs", onde será armazenada a lista de curtidas mútuas. A lógica por trás dessa tabela será, o candidato X curti a vaga Y, nesse momento o back-end armazena na tabela "curtidas_candidatos" a curtida e em seguida verifica se na tabela "curtidas_vagas" existe o registro da curtida da vaga Y nesse candidato, caso exista é adicionado um registro na tabela "matchs", onde quando o candidato logar, serão procurados os matchs do mesmo. O mesmo processo vale para quando a vaga curtir um candidato, diferenciando apenas algumas tabelas. <br> <br> Resumo dos passos: <br>  - Candidato X curti vaga Y <br>  - Aplicação armazena a curtida na tabela "curtidas_candidatos"<br>  - Verifica se a vaga Y já curtiu o candidato na tabela "curtidas_vagas" <br>  - Se existir a curtida, é armazenado o candidato X e a vaga Y na tabela "matchs"<br>  - Ao logar, tanto a empresa como o candidato, a aplicação consulta a tabela "matchs" e retorna a lista de curtidas mútuas.

<div align="center">
    <img src="https://imageup.me/images/bf699f73-b491-4e58-8682-629293a10825.png" />
    <p align="center">DER realizado com <a href="https://dbdiagram.io/home">DBDiagram</a>.</p>
</div>

## Tecnologias utilizadas

- `Java`
- `Groovy`
- `PostgreSQL`


## Abrir e rodar o projeto

**Executar pelo terminal**
- Tenha no mínimo o Java 8 instalado.
- Clone esse repositório
- Navegue pelo terminal até a raíz do repositório
- Execute o comando -> java -jar ./Linketinder.jar

**Executar pela IDE**
- Tenha no mínimo o Java 8 e Groovy 4 instalandos.
- Clone esse repositório
- A classe responsável por iniciar a aplicação é MainMenu encontrada dentro do package Menus.
