package view.register

import controller.ControllerCandidato
import model.competencia.DAOCompetenciaCandidato
import utils.view.ClearConsole
import utils.service.Regex
import utils.view.Input
import view.competencia.CompetenciaView

class RegisterCandidatoView {

    static void menu() {
        String name = Input.create(Regex.nome, "Digite o seu nome.")
        String lastname = new Input().create(Regex.sobrenome, "Digite o seu sobrenome.")
        String dateOfBirth = new Input().create(Regex.dataDeNascimento, "Digite a sua data de nascimento no formato dd/mm/aaaa.")
        String email = new Input().create(Regex.email, "Digite o seu email.")
        String cpf = new Input().create(Regex.cpf, "Digite o seu CPF.")
        String country = new Input().create(Regex.pais, "Digite o seu pais.")
        String cep = new Input().create(Regex.cep, "Digite o seu CEP.")
        String description = new Input().create(Regex.descricao, "Digite a sua descrição.")
        String password = new Input().create(Regex.senha, "Digite a sua senha.")

        Map<String, String> candidatoBO = [
                name: name,
                lastname: lastname,
                dateOfBirthString: dateOfBirth,
                email:email,
                cpf:cpf,
                country: country,
                cep: cep,
                description: description,
                password: password
        ]

        Integer idCandidato = new ControllerCandidato().save(candidatoBO)

        ClearConsole.clear()
        CompetenciaView.menu(idCandidato, "candidato")
    }

}
