package view.competencia

import utils.view.ClearConsole

class CompetenciaView {
    static void menu(Integer idUser, String typeUser) {

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
                    CreateCompetenciaView.menu()
                    println ""
                    break
                case "2":
                    ClearConsole.clear()
                    AddCompetenciaView.menu(idUser, typeUser)
                    println ""
                    break
                case "3":
                    ClearConsole.clear()
                    RemoveCompetenciaView.menu(idUser, typeUser)
                    println ""
                    break
                case "4":
                    ClearConsole.clear()
                    ListCompetenciasView.menu(idUser,typeUser)
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
