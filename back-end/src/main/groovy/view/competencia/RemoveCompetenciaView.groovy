package view.competencia

import controller.ControllerCompetencia
import service.competencia.Competencia
import utils.view.ClearConsole

class RemoveCompetenciaView {
    static void menu(Integer idUser, String typeUser) {
        List<Map<String, String>> competencias = null

        switch (typeUser) {
            case "candidato":
                competencias = ControllerCompetencia.getAllCompetenciasCandidato(idUser)
                break
            case "vaga":
                competencias = ControllerCompetencia.getAllCompetenciasVaga(idUser)
                break
        }

        ClearConsole.clear()

        if(competencias.isEmpty()) {
            ClearConsole.clear()
            println "Nenhuma competência para remover."
        } else {
            boolean condition = true
            while (condition) {
                println "Digite o número da competência que deseja remover."
                println ""
                competencias.forEach { e -> println "${e.id} - ${e.name}"}
                println ""

                Scanner sc = new Scanner(System.in)
                String idCompetencia = sc.nextLine()

                if(Competencia.checkExistsInList(competencias, idCompetencia)) {
                    switch (typeUser) {
                        case "candidato":
                            ControllerCompetencia.removeCompetenciaCandidato(idUser, idCompetencia.toInteger())
                            break
                        case "vaga":
                            ControllerCompetencia.removeCompetenciaVaga(idUser, idCompetencia.toInteger())
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
}
