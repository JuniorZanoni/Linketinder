package view.candidato

import model.DBConnection
import model.candidato.DAOCandidato
import model.competencia.DAOCompetenciaCandidato
import service.user.Candidato
import utils.service.Regex
import utils.view.ClearConsole
import utils.view.Input
import view.MainMenu
import utils.service.ConvertStringInLocalDate
import view.competencia.CompetenciaView

class EditCandidato {
    Candidato candidato
    Integer idCandidato = new DAOCandidato(DBConnection.getDBConnection()).getId(candidato)

    EditCandidato(Candidato candidato) {
        this.candidato = candidato
    }

    void menu() {
        boolean condition = true
        while (condition) {
            ClearConsole.clear()
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

            switch (sc.nextLine()) {
                case "1":
                    new DAOCandidato(DBConnection.getDBConnection()).delete(candidato)
                    ClearConsole.clear()
                    MainMenu.main()
                    break
                case "2":
                    String name = Input.create(Regex.nome, "Digite o novo nome.")
                    candidato.name = name
                    new DAOCandidato(DBConnection.getDBConnection()).update(candidato, idCandidato)
                    break
                case "3":
                    String lastname = Input.create(Regex.sobrenome, "Digite o novo sobrenome.")
                    candidato.lastname = lastname
                    new DAOCandidato(DBConnection.getDBConnection()).update(candidato, idCandidato)
                    break
                case "4":
                    String dateOfBirth = Input.create(Regex.dataDeNascimento, "Digite a nova data de nascimento no formato dd/mm/aaaa.")
                    candidato.dateOfBirth = ConvertStringInLocalDate.convert(dateOfBirth)
                    new DAOCandidato(DBConnection.getDBConnection()).update(candidato, idCandidato)
                    break
                case "5":
                    String email = Input.create(Regex.email, "Digite o novo e-mail.")
                    candidato.email = email
                    new DAOCandidato(DBConnection.getDBConnection()).update(candidato, idCandidato)
                    break
                case "6":
                    String cpf = Input.create(Regex.cpf, "Digite o novo CPF.")
                    candidato.cpf = cpf
                    new DAOCandidato(DBConnection.getDBConnection()).update(candidato, idCandidato)
                    break
                case "7":
                    String country = Input.create(Regex.pais, "Digite o novo país.")
                    candidato.country = country
                    new DAOCandidato(DBConnection.getDBConnection()).update(candidato, idCandidato)
                    break
                case "8":
                    String cep = Input.create(Regex.cep, "Digite o novo CEP.")
                    candidato.cep = cep
                    new DAOCandidato(DBConnection.getDBConnection()).update(candidato, idCandidato)
                    break
                case "9":
                    String description = Input.create(Regex.descricao, "Digite a nova descrição.")
                    candidato.description = description
                    new DAOCandidato(DBConnection.getDBConnection()).update(candidato, idCandidato)
                    break
                case "10":
                    String password = Input.create(Regex.senha, "Digite a nova senha.")
                    candidato.password = password
                    new DAOCandidato(DBConnection.getDBConnection()).update(candidato, idCandidato)
                    break
                case "11":
                    ClearConsole.clear()
                    CompetenciaView.menu(idCandidato, new DAOCompetenciaCandidato(DBConnection.getDBConnection()))
                    break
                case "0":
                    ClearConsole.clear()
                    condition = false
                    break
            }
        }
    }
}
