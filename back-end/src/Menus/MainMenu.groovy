package Menus

import Utils.Utils

class MainMenu {

    static void main(String[] args) {
        Utils.populateCandidates()
        Utils.populateCompanies()

        Utils.clearConsole()
        homeMenu()
    }

    static homeMenu() {
        println "0 - Candidato"
        println "1 - Empresa"
        println "2 - Administração"
        println "3 - Sair"

        Integer option = Utils.selectInteger(3)

        switch (option) {
            case 0:
                new MenuSelectCandidate().menu()
                break
            case 1:
                new MenuSelectCompany().menu()
                break
            case 2:
                Utils.clearConsole()
                new MenuAdministration().menu()
                break
            case 3:
                System.exit(0)
                break
            default: homeMenu()
        }
    }
}
