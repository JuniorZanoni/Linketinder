package view.empresa.vaga.editVaga

import model.DBConnection
import model.ModelVaga
import model.modelCompetencia.ModelCompetenciaVaga
import service.vaga.Vaga
import utils.service.Regex
import utils.view.ClearConsole
import utils.view.Input
import view.competencia.Competencia

class EditVagaOptions {
    Vaga vaga
    Integer idEmpresa
    Integer idVaga = new ModelVaga(DBConnection.getDBConnection()).getId(vaga, idEmpresa)

    EditVagaOptions(Vaga vaga, Integer idEmpresa) {
        this.vaga = vaga
        this.idEmpresa = idEmpresa
    }

    void menu() {
        boolean condition = true
        while (condition) {
            println "1 - Editar nome."
            println "2 - Editar descrição."
            println "3 - Editar local."
            println "4 - Editar competências."
            println "5 - Excluir vaga."
            println ""
            println "0 - Voltar"

            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "1":
                    ClearConsole.clear()
                    vaga.name = Input.create(Regex.descricao, "Digite o novo nome da vaga.")
                    new ModelVaga(DBConnection.getDBConnection()).update(vaga, idVaga)
                    ClearConsole.clear()
                    break
                case "2":
                    ClearConsole.clear()
                    vaga.description = Input.create(Regex.descricao, "Digite a nova descrição da vaga.")
                    new ModelVaga(DBConnection.getDBConnection()).update(vaga, idVaga)
                    ClearConsole.clear()
                    break
                case "3":
                    ClearConsole.clear()
                    vaga.local = Input.create(Regex.descricao, "Digite o novo local da vaga.")
                    new ModelVaga(DBConnection.getDBConnection()).update(vaga, idVaga)
                    ClearConsole.clear()
                    break
                case "4":
                    ClearConsole.clear()
                    Competencia.menu(idVaga, new ModelCompetenciaVaga(DBConnection.getDBConnection()))
                    break
                case "5":
                    ClearConsole.clear()
                    new ModelVaga(DBConnection.getDBConnection()).delete(idVaga)
                    condition = false
                    break
                case "0":
                    ClearConsole.clear()
                    condition = false
                    break
            }
        }
    }
}
