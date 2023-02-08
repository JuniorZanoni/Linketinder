package Menus

import Model.ModelCandidato
import Model.ModelEmpresa
import Utils.Utils

import java.util.regex.Pattern

class Login {

    void menu() {
        String email = inputEmail()
        String senha = inputSenha()

        String emailCandidato = new ModelCandidato().getByEmailAndPass(email, senha)
        String emailEmpresa = new ModelEmpresa().getByEmailAndPass(email, senha)

        if (emailCandidato) {
            Utils.clearConsole()
            new MenuCandidato(emailCandidato).menu()
        } else if (emailEmpresa) {
            Utils.clearConsole()
            new MenuEmpresa(emailEmpresa).menu()
        }

        Utils.clearConsole()
        println "Login ou senha inválidos, tente novamente."
        println ""
        MainMenu.menu()
    }

    String inputEmail() {
        String regex = '^(.+)@(\\S+)\\.(.+)$'

        while (true) {
            Utils.clearConsole()
            System.out.println("Qual seu e-mail.")
            Scanner sc = new Scanner(System.in)
            String email = sc.nextLine()

            if (Pattern.compile(regex).matcher(email).find()) {
                return email
            }
        }
    }

    String inputSenha() {
        String regex = '(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#_!\\-])[0-9a-zA-Z$*&@#_!-]{6,}'

        while (true) {
            Utils.clearConsole()
            System.out.println("Digite sua senha.")
            Scanner sc = new Scanner(System.in)
            String senha = sc.nextLine()

            if (Pattern.compile(regex).matcher(senha).find()) {
                return senha
            }
        }
    }
}
