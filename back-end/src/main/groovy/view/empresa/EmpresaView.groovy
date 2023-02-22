package view.empresa

import service.user.Empresa
import utils.view.ClearConsole
import view.empresa.vaga.VagaView

class EmpresaView {
    Empresa empresa

    EmpresaView(Empresa empresa) {
        this.empresa = empresa
    }

    void menu(){
        boolean condition = true
        while (condition) {
            println "1 - Editar conta."
            println "2 - Vagas."
            println "0 - Voltar."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "1":
                    new EditEmpresaView(empresa).menu()
                    break
                case "2":
                    ClearConsole.clear()
                    new VagaView(empresa).menu()
                    break
                case "0":
                    condition = false
                    break
            }
        }
    }
}
