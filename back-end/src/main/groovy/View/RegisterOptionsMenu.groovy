package View

import Utils.Utils
import View.Candidato.RegisterCandidato
import View.Empresa.RegisterEmpresa

class RegisterOptionsMenu {

    void menu() {
        while (true) {
            Utils.clearConsole()
            println "1 - Cadastrar candidato."
            println "2 - Cadastrar empresa."
            println ""
            println "0 - Voltar."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "1":
                    new RegisterCandidato().menu()
                    break
                case "2":
                    new RegisterEmpresa().menu()
                    break
                case "0":
                    MainMenu.main()
                    break
            }
        }
    }
}
