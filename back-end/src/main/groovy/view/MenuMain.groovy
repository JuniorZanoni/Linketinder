package view

import utils.view.ClearConsole
import view.login.Login
import view.register.Register

class MenuMain {
    static void main(String[] args) {
        boolean condition = true
        while (condition) {
            ClearConsole.clear()
            println "1 - Cadastro."
            println "2 - Login."
            println ""
            println "0 - Sair."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "0":
                    condition = false
                    break
                case "1":
                    Register.menu()
                    break
                case "2":
                    Login.menu()
                    break
            }
        }
    }
}
