package Service

abstract class Usuario {
    String nome, email, pais, cep, descricao, senha

    Usuario(String nome, String email, String pais, String cep, String descricao, String senha) {
        this.nome = nome
        this.email = email
        this.pais = pais
        this.cep = cep
        this.descricao = descricao
        this.senha = senha
    }
}
