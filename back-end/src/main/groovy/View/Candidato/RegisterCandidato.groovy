package View.Candidato

import Model.DBConnection
import Model.ModelCandidato
import Service.Candidato
import Utils.Utils
import View.MainMenu

import java.time.LocalDate

class RegisterCandidato {
    void menu() {
        String nome = Utils.inputString(Utils.regexNome, "Digite o seu nome.")
        String sobrenome = Utils.inputString(Utils.regexSobrenome, "Digite o seu sobrenome.")
        LocalDate dataDeNascimento = Utils.inputDate(Utils.regexDataDeNascimento, "Digite a sua data de nascimento no formato dd/mm/aaaa.")
        String email = Utils.inputString(Utils.regexEmail, "Digite o seu e-mail.")
        String cpf = Utils.inputString(Utils.regexCpf, "Digite o seu cpf.")
        String pais = Utils.inputString(Utils.regexPais, "Digite o seu pais.")
        String cep = Utils.inputString(Utils.regexCep, "Digite o seu cep.")
        String descricao = Utils.inputString(Utils.regexDescricao, "Digite a sua descrição.")
        String senha = Utils.inputString(Utils.regexSenha, "Digite a sua senha.")

        Candidato candidato = new Candidato(nome, email, pais, cep, descricao, senha, sobrenome, cpf, dataDeNascimento)
        new ModelCandidato(new DBConnection()).save(candidato)

        MainMenu.main()
    }
}
