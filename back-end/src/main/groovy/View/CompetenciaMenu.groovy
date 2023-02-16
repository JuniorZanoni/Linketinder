package View

import Model.DBConnection
import Model.ModelCompetencia
import Service.Candidato
import Utils.Utils
import View.Candidato.EditCandidato

class CompetenciaMenu {

    Candidato candidato

    CompetenciaMenu(Candidato candidato) {
        this.candidato = candidato
    }

    void menu() {
        Utils.clearConsole()

        while (true) {
            println "1 - Cadastrar competência."
            println "2 - Adicionar competência."
            println "3 - Remover competência."
            println "4 - Listar competências."
            println ""
            println "0 - Voltar."

            Scanner sc = new Scanner(System.in)

            switch (sc.nextInt()) {
                case 0:
                    Utils.clearConsole()
                    new EditCandidato(candidato).menu()
                    println ""
                    break
                case 1:
                    Utils.clearConsole()
                    createCompetencia()
                    println ""
                    break
                case 2:
                    Utils.clearConsole()
                    addCompetencia()
                    println ""
                    break
                case 3:
                    Utils.clearConsole()
                    removeCompetencia()
                    println ""
                    break
                case 4:
                    Utils.clearConsole()
                    listCompenteicasUser()
                    println ""
                    break
                default: Utils.clearConsole()
            }
        }
    }

    private void createCompetencia() {
        String regex = /([A-Za-zéãíóúç]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Digite a nova competência."
            Scanner sc = new Scanner(System.in)
            String competencia = sc.nextLine()

            if (competencia.matches(regex)) {
                new ModelCompetencia(DBConnection).saveCompetencia(competencia)
                break
            }
        }

        Utils.clearConsole()
    }

    private void listCompenteicasUser() {
        List competencias = new ModelCompetencia(DBConnection).loadCompetenciasUser(candidato)

        if(competencias.size() == 0) {
            Utils.clearConsole()
            println "Você não tem nenhuma competência cadastrada."
            println ""
            println "Pressione enter para continuar."
            Scanner sc = new Scanner(System.in)
            sc.nextLine()
            menu()
        }

        competencias.forEach { e -> println "${e[0]} - ${e[1]}"}
    }

    private void addCompetencia() {
        Utils.clearConsole()
        while (true) {
            println "Digite o número da competência que deseja adicionar."
            List competenciasNotUser = new ModelCompetencia(DBConnection).loadCompetenciasNotUser(candidato)
            competenciasNotUser.forEach { e -> println "${e[0]} - ${e[1]}"}

            Scanner sc = new Scanner(System.in)

            try {
                Integer index = sc.nextInt()
                if(competenciasNotUser.find(e -> e[0] == index.toString())) {
                    new ModelCompetencia(DBConnection).saveCompeteciaUser(candidato, index)
                    menu()
                } else {
                    throw new Exception()
                }
            } catch (Exception e) {
                Utils.clearConsole()
                println "Competência inválida."
                println ""
            }
        }
    }

    private void removeCompetencia() {
        Utils.clearConsole()

        List competenciasUser = new ModelCompetencia(DBConnection).loadCompetenciasUser(candidato)

        if(competenciasUser.size() == 0) {
            Utils.clearConsole()
            println "Você não tem nenhuma competência cadastrada."
            println ""
            println "Pressione enter para continuar."
            Scanner sc = new Scanner(System.in)
            sc.nextLine()
            menu()
        }

        while (true) {
            println "Digite o número da competência que deseja remover."
            competenciasUser.forEach { e -> println "${e[0]} - ${e[1]}"}

            Scanner sc = new Scanner(System.in)

            try {
                Integer index = sc.nextInt()
                if(competenciasUser.find(e -> e[0] == index.toString())) {
                    new ModelCompetencia(DBConnection).removeCompetenciaUser(candidato, index)
                    menu()
                } else {
                    throw new Exception()
                }
            } catch (Exception e) {
                Utils.clearConsole()
                println "Competência inválida."
                println ""
            }
        }
    }
}
