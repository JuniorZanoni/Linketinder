package Usuario

import Model.ModelCandidato
import org.codehaus.groovy.util.ListHashMap

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

    void save() {
        new ModelCandidato().save(infosCandidato())
    }

    void update() {
        new ModelCandidato().update(infosCandidato())
    }

    private ListHashMap infosCandidato() {
        return [
                nome: this.nome,
                sobrenome: this.sobrenome,
                dataDeNascimento: this.dataDeNascimento,
                email: this.email,
                cpf: this.cpf,
                pais: this.pais,
                cep: this.cep,
                descricao: this.descricao,
                senha: this.senha
        ]
    }
}
