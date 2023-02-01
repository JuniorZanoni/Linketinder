package Menus

import Database.CandidateData
import Database.CompanyData
import Persons.CandidatePerson
import Persons.CompanyPerson
import Utils.Utils

class MenuCompany {
    void menu(Integer index) {
        println "0 - Curtir candidatos"
        println "1 - Ver matches"
        println "2 - Sair"

        Integer option = Utils.selectInteger(2)

        switch (option) {
            case 0:
                menuLike(index)
                break
            case 1:
                menuMatches(index)
                break
            case 2:
                new MenuSelectCompany().menu()
                break
            default: menu(index)
        }
    }

    void menuLike(Integer index) {
        CompanyPerson company = CompanyData.companies[index]
        List<CandidatePerson> candidates = CandidateData.candidates

        candidates.each { candidate ->
            {
                Integer option = -1
                def menuLike = {
                    Utils.clearConsole()
                    println candidate.getName()
                    println candidate.getDescription()
                    println "Esse dev conhece: ${candidate.getSkills()}"
                    println ""
                    println "0 - Like"
                    println "1 - Passar"

                    option = Utils.selectInteger(1)
                }

                while (option == -1) {
                    menuLike()
                }

                if (option == 0) {

                    if (candidate.likes.contains(company)) {
                        company.matches.add(candidate)
                        candidate.matches.add(company)
                    } else {
                        company.likes.add(candidate)
                    }
                }
            }
        }

        Utils.clearConsole()
        new MenuCompany().menu(index)
    }

    void menuMatches(Integer index) {
        CompanyPerson company = CompanyData.companies[index]

        Utils.clearConsole()
        company.matches.each { println "${it.name} - ${it.description}"}
        println ""

        new MenuCompany().menu(index)
    }
}
