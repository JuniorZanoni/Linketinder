package view.empresa.vaga

import service.user.Empresa
import utils.view.ClearConsole
import view.curtir.CurtirVagaView
import view.empresa.vaga.editVaga.EditVaga
import view.matches.MatchesVagaView

class VagaView {

    Empresa empresa

    VagaView(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        boolean condition = true
        while (condition) {
            println "1 - Criar vaga."
            println "2 - Editar vagas."
            println "3 - Listar vagas."
            println "4 - Curtir candidatos."
            println "5 - Matches."
            println ""
            println "0 - Voltar."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "1":
                    new CreateVaga(empresa).menu()
                    ClearConsole.clear()
                    break
                case "2":
                    new EditVaga(empresa).menu()
                    ClearConsole.clear()
                    break
                case "3":
                    new ListVagas(empresa).menu()
                    break
                case "4":
                    new CurtirVagaView(empresa).menu()
                    break
                case "5":
                    new MatchesVagaView(empresa).menu()
                    break
                case "0":
                    condition = false
                    break
            }
        }
    }
}
