package view.competencia


import model.modelCompetencia.IModelCompetencia
import service.competencia.Competencia
import utils.view.ClearConsole

class AddCompetencia {

    static void menu(Integer id, IModelCompetencia Model) {
        List competenciasNotUser = Model.getCompetenciasNoHave(id)
        List competenciasNotUserBO = Competencia.convertListInBO(competenciasNotUser)

        ClearConsole.clear()

        boolean condition = true
        while (condition) {
            println "Digite o número da competência que deseja adicionar."
            println ""
            competenciasNotUserBO.forEach { e -> println "${e.id} - ${e.name}"}
            println ""

            Scanner sc = new Scanner(System.in)

            String idCompetencia = sc.nextLine()
            if(Competencia.checkExistsInList(competenciasNotUserBO, idCompetencia)) {
                Model.saveCompetecia(id, idCompetencia.toInteger())
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
