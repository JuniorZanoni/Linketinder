package Menus

import Database.CandidateData
import Database.CompanyData
import Persons.CandidatePerson
import Persons.CompanyPerson
import Utils.Utils

class MenuCandidate {

    void menu(Integer index){
        println "0 - Curtir empresas"
        println "1 - Ver matches"
        println "2 - Voltar"

        Integer option = Utils.selectInteger(2)

        switch (option) {
            case 0:
                menuLike(index)
                break
            case 1:
                menuMatches(index)
                break
            case 2:
                new MenuSelectCandidate().menu()
                break
            default: menu(index)
        }
    }

    void menuLike(Integer index) {
        CandidatePerson candidate = CandidateData.candidates[index]
        List<CompanyPerson> companies = CompanyData.companies

        companies.each { company -> {
            Integer option = -1
            def menuLike = {
                Utils.clearConsole()
                println company.getName()
                println company.getDescription()
                println "Procuramos por desenvolvedores que conheçam: ${company.getSkills()}"
                println ""
                println "0 - Like"
                println "1 - Dislike"

                option = Utils.selectInteger(1)
            }

            while(option == -1) {
                menuLike()
            }

            if(option == 0) {

                if(company.likes.contains(candidate)) {
                    candidate.matches.add(company)
                    company.matches.add(candidate)
                } else {
                    candidate.likes.add(company)
                }
            }
        }}

        Utils.clearConsole()
        new MenuCandidate().menu(index)
    }

    void menuMatches(Integer index) {
        CandidatePerson candidate = CandidateData.candidates[index]

        Utils.clearConsole()
        candidate.matches.each { println "${it.name} - ${it.description}"}
        println ""

        new MenuCandidate().menu(index)
    }
}
