package view.empresa.vaga.editVaga

import model.DBConnection
import model.DAOEmpresa
import model.ModelVaga
import service.user.empresa.Empresa
import service.vaga.Vaga
import utils.view.ClearConsole
import view.empresa.vaga.ListVagas

class EditVaga {

    Empresa empresa
    Integer idEmpresa = new DAOEmpresa(DBConnection.getDBConnection()).getId(empresa)

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

            List<Vaga> vagas = new ModelVaga(DBConnection.getDBConnection()).getAllVagasByEmpresa(idEmpresa)

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
