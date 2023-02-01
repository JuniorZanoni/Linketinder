package Menus

import Database.CandidateData
import Persons.CandidatePerson
import Utils.Utils

class MenuSelectCandidate {

    void menu() {
        Integer option = selectCandidate()

        Utils.clearConsole()
        new MenuCandidate().menu(option)
    }

    Integer selectCandidate() {
        Utils.clearConsole()

        List<CandidatePerson> candidates = CandidateData.candidates
        candidates.eachWithIndex { candidate, index -> println "${index} - ${candidate.getName()}"}
        println "${candidates.size()} - Voltar"

        Integer option = Utils.selectInteger(candidates.size())

        switch (option) {
            case candidates.size():
                Utils.clearConsole()
                MainMenu.homeMenu()
                break
            case -1:
                selectCandidate()
                break
            default: return option
        }

    }

}
