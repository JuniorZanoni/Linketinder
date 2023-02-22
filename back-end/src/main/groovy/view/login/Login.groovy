package view.login

import model.DBConnection
import model.DAOCandidato
import model.DAOEmpresa
import service.user.candidato.Candidato
import service.user.empresa.Empresa
import utils.service.Regex
import utils.view.ClearConsole
import utils.view.Input
import view.candidato.CandidatoView
import view.empresa.EmpresaView

class Login {
    static void menu() {
        String email = new Input().create(Regex.email, "Digite o seu email.")
        String password = new Input().create(Regex.senha, "Digite a sua senha.")

        Candidato candidato = new DAOCandidato(DBConnection.getDBConnection()).getCandidato(email, password)
        Empresa empresa = new DAOEmpresa(DBConnection.getDBConnection()).getEmpresa(email, password)

        if(candidato) {
            ClearConsole.clear()
            new CandidatoView(candidato).menu()
        } else if(empresa) {
            ClearConsole.clear()
            new EmpresaView(empresa).menu()
        }
    }
}
