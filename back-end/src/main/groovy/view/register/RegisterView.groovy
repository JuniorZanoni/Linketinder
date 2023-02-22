package view.register

import utils.view.ClearConsole

class RegisterView {
    static void menu() {
        boolean condition = true
        while (condition) {
            ClearConsole.clear()
            println "1 - Cadastrar candidato."
            println "2 - Cadastrar empresa."
            println ""
            println "0 - Voltar."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "0":
                    condition = false
                    break
                case "1":
                    RegisterCandidatoView.menu()
                    break
                case "2":
                    RegisterEmpresaView.menu()
                    break
            }
        }
    }
}
