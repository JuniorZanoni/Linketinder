package Menus

import Model.ModelCandidato
import Model.ModelEmpresa
import Usuario.Candidato
import Usuario.Empresa
import Utils.Utils

class Login {

    void menu() {
        while (true) {
            List logins = login()
            nextMenu(logins)
        }
    }

    private List login() {
        String email = Utils.inputString(Utils.regexEmail, "Digite seu e-mail.")
        String senha = Utils.inputString(Utils.regexSenha, "Digite sua senha.")

        Candidato candidato = new ModelCandidato().get(email, senha)
        Empresa empresa = new ModelEmpresa().get(email, senha)

        return [candidato, empresa]
    }

    private void nextMenu(List logins) {
        if (logins[0]) {
            Utils.clearConsole()
            new MenuCandidato(logins[0]).menu()
        } else if (logins[1]) {
            Utils.clearConsole()
            new MenuEmpresa(logins[1]).menu()
        } else {
            Utils.clearConsole()
            println "Login ou senha inválidos, tente novamente."
            println ""
            MainMenu.menu()
        }
    }
}
