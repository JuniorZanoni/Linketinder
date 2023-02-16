package View

import Model.DBConnection
import Model.ModelCandidato
import Model.ModelEmpresa
import Service.Candidato
import Service.Empresa
import Utils.Utils
import View.Candidato.MenuCandidato
import View.Empresa.MenuEmpresa

class Login {
    void menu() {
        String email = Utils.inputString(Utils.regexEmail, "Digite o seu e-mail.")
        String senha = Utils.inputString(Utils.regexSenha, "Digite a sua senha.")

        Candidato candidato = new ModelCandidato(DBConnection).load(email, senha)
        Empresa empresa = new ModelEmpresa(DBConnection).load(email, senha)

        if(candidato) {
            Utils.clearConsole()
            new MenuCandidato(candidato).menu()
        } else if(empresa) {
            Utils.clearConsole()
            new MenuEmpresa(empresa).menu()
        }
    }
}
