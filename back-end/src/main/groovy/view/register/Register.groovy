package view.register

import utils.view.ClearConsole

class Register {
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
                    RegisterCandidato.menu()
                    break
                case "2":
                    RegisterEmpresa.menu()
                    break
            }
        }
    }
}
