package View.Candidato

import Model.DBConnection
import Model.ModelCandidato
import Service.Candidato
import Utils.Utils
import View.CompetenciaMenu
import View.MainMenu

import java.time.LocalDate

class EditCandidato {
    Candidato candidato

    EditCandidato(Candidato candidato) {
        this.candidato = candidato
    }

    void menu() {
        while (true) {
            Utils.clearConsole()
            println "1 - Apagar conta."
            println "2 - Editar nome."
            println "3 - Editar sobrenome."
            println "4 - Editar data de nascimento."
            println "5 - Editar e-mail."
            println "6 - Editar CPF."
            println "7 - Editar país."
            println "8 - Editar CEP."
            println "9 - Editar descrição."
            println "10 - Editar senha."
            println "11 - Editar competências."
            println ""
            println "0 - Voltar."

            Scanner sc = new Scanner(System.in)

            switch (sc.nextInt()) {
                case 1:
                    new ModelCandidato(DBConnection).delete(candidato)
                    Utils.clearConsole()
                    MainMenu.main()
                    break
                case 2:
                    String nome = Utils.inputString(Utils.regexNome, "Digite o novo nome.")
                    candidato.nome = nome
                    new ModelCandidato(DBConnection).update(candidato)
                    break
                case 3:
                    String sobrenome = Utils.inputString(Utils.regexSobrenome, "Digite o novo sobrenome.")
                    candidato.sobrenome = sobrenome
                    new ModelCandidato(DBConnection).update(candidato)
                    break
                case 4:
                    LocalDate dataDeNascimento = Utils.inputDate(Utils.regexDataDeNascimento, "Digite a nova data de nascimento.")
                    candidato.dataDeNascimento = dataDeNascimento
                    new ModelCandidato(DBConnection).update(candidato)
                    break
                case 5:
                    String email = Utils.inputString(Utils.regexEmail, "Digite o novo email.")
                    new ModelCandidato(DBConnection).updateEmail(candidato, email)
                    candidato.email = email
                    break
                case 6:
                    String cpf = Utils.inputString(Utils.regexCpf, "Digite o novo cpf.")
                    candidato.cpf = cpf
                    new ModelCandidato(DBConnection).update(candidato)
                    break
                case 7:
                    String pais = Utils.inputString(Utils.regexPais, "Digite o novo pais.")
                    candidato.pais = pais
                    new ModelCandidato(DBConnection).update(candidato)
                    break
                case 8:
                    String cep = Utils.inputString(Utils.regexCep, "Digite o novo cep.")
                    candidato.cep = cep
                    new ModelCandidato(DBConnection).update(candidato)
                    break
                case 9:
                    String descricao = Utils.inputString(Utils.regexDescricao, "Digite a nova descricao.")
                    candidato.descricao = descricao
                    new ModelCandidato(DBConnection).update(candidato)
                    break
                case 10:
                    String senha = Utils.inputString(Utils.regexSenha, "Digite a nova senha.")
                    candidato.senha = senha
                    new ModelCandidato(DBConnection).update(candidato)
                    break
                case 11:
                    new CompetenciaMenu(candidato).menu()
                    break
                case 0:
                    Utils.clearConsole()
                    new MenuCandidato(candidato).menu()
                    break
            }
        }
    }
}
