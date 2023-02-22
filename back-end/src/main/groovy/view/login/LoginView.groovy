package view.login

import controller.ControllerLogin
import service.user.Candidato
import service.user.Empresa
import service.user.User
import utils.service.Regex
import utils.view.ClearConsole
import utils.view.Input
import view.candidato.CandidatoView
import view.empresa.EmpresaView

class LoginView {
    static void menu() {
        String email = new Input().create(Regex.email, "Digite o seu email.")
        String password = new Input().create(Regex.senha, "Digite a sua senha.")

        User user = ControllerLogin.login(email, password)

        switch (user.getClass().getSimpleName()) {
            case "Candidato":
                ClearConsole.clear()
                new CandidatoView(user as Candidato).menu()
                break
            case "Empresa":
                ClearConsole.clear()
                new EmpresaView(user as Empresa).menu()
                break
        }
    }
}
