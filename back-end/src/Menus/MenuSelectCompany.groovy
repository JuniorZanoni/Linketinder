package Menus

import Database.CompanyData
import Persons.CompanyPerson
import Utils.Utils

class MenuSelectCompany {

    void menu() {
        Integer option = selectCompany()

        Utils.clearConsole()
        new MenuCompany().menu(option)
    }

    Integer selectCompany() {
        Utils.clearConsole()
        println "Escolha uma empresa"

        List<CompanyPerson> companys = CompanyData.companies
        companys.eachWithIndex { company, index -> println "${index} - ${company.getName()}"}
        println "${companys.size()} - Sair"

        Integer option = Utils.selectInteger(companys.size())

        switch (option) {
            case companys.size():
                Utils.clearConsole()
                MainMenu.homeMenu()
                break
            case -1:
                return selectCompany()
                break
            default: return option
        }
    }
}
