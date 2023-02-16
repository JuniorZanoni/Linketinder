package View

import Utils.Utils

class MainMenu {

    static void main(String[] args) {
        while (true) {
            Utils.clearConsole()
            println "1 - Cadastro."
            println "2 - Login."
            println ""
            println "0 - Sair."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "1":
                    new RegisterOptionsMenu().menu()
                    break
                case "2":
                    new Login().menu()
                    break
                case "0":
                    System.exit(0)
                    break
            }
        }
    }
}
