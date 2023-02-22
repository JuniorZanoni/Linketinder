package view.competencia

import controller.ControllerCompetencia
import service.competencia.Competencia
import utils.view.ClearConsole

class AddCompetenciaView {

    static void menu(Integer idUser, String typeUser) {
        List<Map<String, String>> competenciasNotUserBO

        if(typeUser == "candidato") {
            competenciasNotUserBO = ControllerCompetencia.getCompetenciasNoHaveCandidato(idUser)
        } else {
            competenciasNotUserBO = ControllerCompetencia.getCompetenciasNoHaveVaga(idUser)
        }

        ClearConsole.clear()

        boolean condition = true
        while (condition) {
            println "Digite o número da competência que deseja adicionar."
            println ""
            competenciasNotUserBO.forEach { competencia -> println "${competencia.id} - ${competencia.name}"}
            println ""

            Scanner sc = new Scanner(System.in)
            String idCompetencia = sc.nextLine()

            if(Competencia.checkExistsInList(competenciasNotUserBO, idCompetencia)) {
                switch (typeUser) {
                    case "candidato": ControllerCompetencia.addCompetenciaCandidato(idUser, idCompetencia.toInteger())
                        break
                    case "vaga": ControllerCompetencia.addCompetenciaVaga(idUser, idCompetencia.toInteger())
                        break
                }

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
