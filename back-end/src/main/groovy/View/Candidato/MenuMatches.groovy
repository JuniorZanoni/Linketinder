package View.Candidato

import Model.DBConnection
import Model.ModelMatches
import Service.Candidato
import Utils.Utils

class MenuMatches {
    Candidato candidato

    MenuMatches(Candidato candidato) {
        this.candidato = candidato
    }

    void menu() {
        Utils.clearConsole()
        List vagas = new ModelMatches(DBConnection).getMatchesCandidato(candidato)

        if (vagas.size() > 0) {
            vagas.forEach(vaga -> {
                println "${vaga.empresa} - ${vaga.vaga} - ${vaga.descricao} - ${vaga.local}"
            })

            println ""
            new MenuCandidato(candidato).menu()
        } else {
            Utils.clearConsole()
            println "Você não tem matches."
            println ""
            new MenuCandidato(candidato).menu()
        }
    }
}
