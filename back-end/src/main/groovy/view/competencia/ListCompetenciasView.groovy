package view.competencia

import controller.ControllerCompetencia
import utils.view.ClearConsole

class ListCompetenciasView {

   static void menu(Integer idUser, String typeUser) {
       List<Map> competencias

       if(typeUser == "candidato") {
           competencias = ControllerCompetencia.getAllCompetenciasCandidato(idUser)
       } else {
           competencias = ControllerCompetencia.getAllCompetenciasVaga(idUser)
       }

        if(competencias.isEmpty()) {
            ClearConsole.clear()
            println "Nenhuma competência cadastrada."
        } else {
            competencias.forEach { competencia -> println "${competencia.id} - ${competencia.name}"}
        }
    }
}
