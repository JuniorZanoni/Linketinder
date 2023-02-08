package Usuario

import java.time.LocalDate

class Candidato {
    String nome, sobrenome, email, cpf, pais, cep, descricao, senha
    LocalDate dataDeNascimento

    Candidato(String nome, String sobrenome, LocalDate dataDeNascimento, String email, String cpf, String pais, String cep, String descricao, String senha) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.dataDeNascimento = dataDeNascimento
        this.email = email
        this.cpf = cpf
        this.pais = pais
        this.cep = cep
        this.descricao = descricao
        this.senha = senha
    }
}
