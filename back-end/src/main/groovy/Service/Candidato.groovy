package Service

import java.time.LocalDate

class Candidato extends Usuario {
    String  sobrenome, cpf
    LocalDate dataDeNascimento

    Candidato(String nome, String email, String pais, String cep, String descricao, String senha, String sobrenome, String cpf, LocalDate dataDeNascimento) {
        super(nome, email, pais, cep, descricao, senha)
        this.sobrenome = sobrenome
        this.cpf = cpf
        this.dataDeNascimento = dataDeNascimento
    }
}
