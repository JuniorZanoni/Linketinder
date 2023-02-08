package Menus

import Utils.Utils

class MainMenu {

    static void main(String[] args) {
        Utils.clearConsole()
        menu()
    }

    static menu() {
        while (true) {
            println "1 - Cadastro"
            println "2 - Login"
            println "3 - Sair"

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        opcoesCadastro()
                        break
                    case 2:
                        new Login().menu()
                        break
                    case 3:
                        System.exit(0)
                        break
                }
            } catch (Exception) {
                Utils.clearConsole()
            }
        }
    }

    static opcoesCadastro() {
        while (true) {
            Utils.clearConsole()
            println "1 - Cadastrar candidato."
            println "2 - Cadastrar empresa."
            println "3 - Voltar."

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        new CadastroCandidato().menu()
                        break
                    case 2:
                        new CadastroEmpresa().menu()
                        break
                    case 3:
                        Utils.clearConsole()
                        MainMenu.menu()
                        break
                }
            } catch (Exception) {
            }
        }
    }
}
