package view.competencia

import controller.ControllerCompetencia
import utils.view.ClearConsole

class ListCompetenciasView {

   static void menu(Integer idUser, String typeUser) {
       List<Map<String, String>> competencias = ControllerCompetencia.getAllCompetenciasCandidato(idUser)

       switch (typeUser) {
           case "candidato": competencias = ControllerCompetencia.getAllCompetenciasCandidato(idUser)
               break
           case "vaga": println "vagaaaaaaaaaa"
       }

        if(competencias.isEmpty()) {
            ClearConsole.clear()
            println "Nenhuma competência cadastrada."
        } else {
            competencias.forEach { competencia -> println "${competencia.id} - ${competencia.name}"}
        }
    }
}
