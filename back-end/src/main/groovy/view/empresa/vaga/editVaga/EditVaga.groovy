package view.empresa.vaga.editVaga

import controller.ControllerEmpresa
import controller.ControllerVaga
import service.user.Empresa
import service.vaga.Vaga
import utils.view.ClearConsole
import view.empresa.vaga.ListVagas

class EditVaga {

    Empresa empresa
    Integer idEmpresa = ControllerEmpresa.getId(empresa)

    EditVaga(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        boolean condition = true
        while(condition) {
            println "Digite o ID da vaga que deseja editar."
            println ""
            new ListVagas(empresa).menu()
            Scanner sc = new Scanner(System.in)
            String idVaga = sc.nextLine()

            List<Vaga> vagas = ControllerVaga.getVagasByEmpresa(idEmpresa)

            try {
                ClearConsole.clear()
                new EditVagaOptions(vagas.get(idVaga.toInteger()), idEmpresa).menu()
                condition = false
            } catch (Exception ignored){
                ClearConsole.clear()
                println "Opção inválida."
            }
        }
    }
}
