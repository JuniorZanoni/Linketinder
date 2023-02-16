package View.Empresa

import Service.Empresa
import Utils.Utils
import View.MainMenu

class MenuEmpresa {
    Empresa empresa

    MenuEmpresa(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        while (true) {
            Utils.clearConsole()
            println "1 - Editar empresa."
            println "2 - Vagas."
            println ""
            println "0 - Voltar."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "1":
                    new EditEmpresa(empresa).menu()
                    break
                case "2":
                    Utils.clearConsole()
                    new MenuVaga(empresa).menu()
                    break
                case "0":
                    MainMenu.main()
                    break
            }
        }
    }
}
