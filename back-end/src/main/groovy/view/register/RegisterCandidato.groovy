package view.register

import model.DBConnection
import model.ModelCandidato
import model.modelCompetencia.ModelCompetenciaCandidato
import service.user.candidato.Candidato
import utils.service.ConvertStringInLocalDate
import utils.view.ClearConsole
import view.competencia.Competencia
import utils.service.Regex
import utils.view.Input

import java.time.LocalDate

class RegisterCandidato {

    static void menu() {
        String name = Input.create(Regex.nome, "Digite o seu nome.")
        String lastName = new Input().create(Regex.sobrenome, "Digite o seu sobrenome.")

        String dateOfBirthString = new Input().create(Regex.dataDeNascimento, "Digite a sua data de nascimento no formato dd/mm/aaaa.")
        LocalDate dateOfBirth = ConvertStringInLocalDate.convert(dateOfBirthString)

        String email = new Input().create(Regex.email, "Digite o seu email.")
        String cpf = new Input().create(Regex.cpf, "Digite o seu CPF.")
        String country = new Input().create(Regex.pais, "Digite o seu pais.")
        String cep = new Input().create(Regex.cep, "Digite o seu CEP.")
        String description = new Input().create(Regex.descricao, "Digite a sua descrição.")
        String password = new Input().create(Regex.senha, "Digite a sua senha.")

        Candidato candidato = new Candidato(name, lastName, cpf, email, dateOfBirth, country, cep, description, password)
        new ModelCandidato(DBConnection.sql).save(candidato)

        ClearConsole.clear()
        Integer idCandidato = new ModelCandidato(DBConnection.sql).getId(candidato)
        Competencia.menu(idCandidato, new ModelCompetenciaCandidato(DBConnection.sql))
    }

}
