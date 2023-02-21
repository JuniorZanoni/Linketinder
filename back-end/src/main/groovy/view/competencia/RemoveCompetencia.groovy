package view.competencia

import model.modelCompetencia.IModelCompetencia
import utils.view.ClearConsole

class RemoveCompetencia {

    static void menu(Integer id, IModelCompetencia Model) {
        List competencias = Model.getCompetencias(id)
        List competenciasBO = service.competencia.Competencia.convertListInBO(competencias)

        ClearConsole.clear()

        if(competenciasBO.isEmpty()) {
            ClearConsole.clear()
            println "Nenhuma competência para remover."
        } else {
            boolean condition = true
            while (condition) {
                println "Digite o número da competência que deseja remover."
                println ""
                competenciasBO.forEach { e -> println "${e.id} - ${e.name}"}
                println ""

                Scanner sc = new Scanner(System.in)

                String idCompetencia = sc.nextLine()
                if(service.competencia.Competencia.checkExistsInList(competenciasBO, idCompetencia)) {
                    Model.deleteCompetecia(id, idCompetencia.toInteger())
                    condition = false
                    ClearConsole.clear()
                } else {
                    ClearConsole.clear()
                    println "Opção inválida."
                    println ""
                }
            }
        }
    }
}
