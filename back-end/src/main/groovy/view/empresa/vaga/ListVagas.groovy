package view.empresa.vaga

import controller.ControllerEmpresa
import controller.ControllerVaga
import model.DBConnection
import model.DAOEmpresa
import model.DAOVaga
import service.user.Empresa
import service.vaga.Vaga
import utils.view.ClearConsole

class ListVagas {

    Empresa empresa
    Integer idEmpresa = ControllerEmpresa.getId(empresa)

    ListVagas(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        List<Vaga> vagas = ControllerVaga.getVagasByEmpresa(idEmpresa)
        if(vagas.isEmpty()) {
            ClearConsole.clear()
            println "Não existem vagas cadastradas."
            println ""
        } else {
            ClearConsole.clear()
            Integer counter = 0
            vagas.forEach {
                println "${counter} - ${it.name} - ${it.description} - ${it.local}"
                counter++
            }
            println ""
        }
    }
}
