# Linketinder ZG

Linketinder ZG é uma aplicação desenvolvida em Groovy com objetivo de desenvolvedores e empresas poderem se encontrar.
Candidatos e empresas podem dar like um no outro, caso o like seja mútuo, é gerado um match, que demonstra que ambas tem
interesse.

## Funcionalidades do projeto

- `Cadastrar candidato`: Cadastrar candidato com os campos, nome, sobrenome, e-mail, CPF, pais, cep, descricao, senha e competências.
- `Cadastrar empresa`: Cadastrar empresa com os campos nome, CNPJ, e-mail, descricao, pais, CEP e senha.
- `Cadastrar de vagas`: Cadastrar vaga com os campos nome, descrição, local e competências.
- `Editar`: Todos os campos de candidatos, empresas e vagas podem ser editados.
- `Curtir`: Exibe candidatos ou vagas, para que o usúario possa curtir.
- `Ver matches`: Caso dois usúarios se curtam, é dado match e pode ser exibida uma lista com os matches.
- `Endpoints`: O projeto está sendo migrado para a arquitetura de microserviços, no momento existem dois endpoints funcionais, POST /candidatos para cadastro de cadidatos e POST /vagas para cadastro de vagas.

## Tecnologias utilizadas

- `Java`
- `Groovy`
- `PostgreSQL`


## Abrir e rodar o projeto

**Executar pelo terminal**
- Tenha no mínimo o Java 8 e PostgreSQL instalados.
- Clone esse repositório.
- Execute o arquivo .sql no PostgresSQL.
- Configure o acesso ao banco de dados na classe Connection dentro do package Model.
- Navegue pelo terminal até a raíz do repositório.
- Execute o comando -> java -jar ./Linketinder.jar.

**Executar pela IDE**
- Tenha no mínimo o Java 8, Groovy 4 e PostgreSQL instalados.
- Clone esse repositório.
- Execute o arquivo .sql no PostgresSQL.
- Configure o acesso ao banco de dados na classe Connection dentro do package Model.
- A classe responsável por iniciar a aplicação é MainMenu encontrada dentro do package Menus.

**Arquivos SQL**
- O arquivo .sql e DER podem ser encontrados [aqui](https://github.com/JuniorZanoni/Linketinder/tree/main/banco-de-dados).

<div align="center">
    <img src="https://github.com/JuniorZanoni/Linketinder/blob/main/banco-de-dados/linketinder-DER.png?raw=true" />
    <p align="center">DER realizado com <a href="https://dbdiagram.io/home">DBDiagram</a>.</p>
</div>