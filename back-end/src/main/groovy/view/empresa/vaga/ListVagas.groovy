package view.empresa.vaga

import model.DBConnection
import model.ModelEmpresa
import model.ModelVaga
import service.user.empresa.Empresa
import service.vaga.Vaga
import utils.view.ClearConsole

class ListVagas {

    Empresa empresa
    Integer idEmpresa = new ModelEmpresa(DBConnection.sql).getId(empresa)

    ListVagas(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        List<Vaga> vagas = new ModelVaga(DBConnection.sql).getAllVagasByEmpresa(idEmpresa)
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
