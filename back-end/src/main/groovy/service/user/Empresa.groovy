package service.user

class Empresa extends User{
    String nome, email, cnpj, descricao, pais, cep, senha

    Empresa(String nome, String email, String cnpj, String descricao, String pais, String cep, String senha) {
        this.nome = nome
        this.email = email
        this.cnpj = cnpj
        this.descricao = descricao
        this.pais = pais
        this.cep = cep
        this.senha = senha
    }
}
