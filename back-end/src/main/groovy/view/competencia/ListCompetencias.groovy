package view.competencia


import model.modelCompetencia.IModelCompetencia
import utils.view.ClearConsole
import service.competencia.Competencia

class ListCompetencias {

   static void menu(Integer id, IModelCompetencia Model) {
        List competencias = Model.getCompetencias(id)
        List<Map> competenciasBO = Competencia.convertListInBO(competencias)

        if(competenciasBO.isEmpty()) {
            ClearConsole.clear()
            println "Nenhuma competência cadastrada."
        } else {
            competenciasBO.forEach { e -> println "${e.id} - ${e.name}"}
        }
    }
}
