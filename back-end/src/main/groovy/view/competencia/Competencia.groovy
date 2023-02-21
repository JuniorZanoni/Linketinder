package view.competencia

import model.modelCompetencia.IModelCompetencia
import utils.view.ClearConsole

class Competencia {
    static void menu(Integer id, IModelCompetencia Model) {

        boolean condition = true
        while (condition) {
            println "1 - Cadastrar competência."
            println "2 - Adicionar competência."
            println "3 - Remover competência."
            println "4 - Listar competências."
            println ""
            println "0 - Voltar."

            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "0":
                    ClearConsole.clear()
                    condition = false
                    break
                case "1":
                    ClearConsole.clear()
                    CreateCompetencia.menu()
                    println ""
                    break
                case "2":
                    ClearConsole.clear()
                    AddCompetencia.menu(id, Model)
                    println ""
                    break
                case "3":
                    ClearConsole.clear()
                    RemoveCompetencia.menu(id, Model)
                    println ""
                    break
                case "4":
                    ClearConsole.clear()
                    ListCompetencias.menu(id, Model)
                    println ""
                    break
                default:
                    ClearConsole.clear()
                    println "Opção inválida"
                    println ""
            }
        }
    }
}
