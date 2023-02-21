package view.matches

import model.DBConnection
import model.ModelMatch
import service.user.candidato.Candidato
import utils.view.ClearConsole

class MatchesCandidatoView {
    Candidato candidato

    MatchesCandidatoView(Candidato candidato) {
        this.candidato = candidato
    }

    void menu() {
        ClearConsole.clear()
        List vagas = new ModelMatch(DBConnection.getDBConnection()).getMatchesCandidato(candidato)

        if (vagas.isEmpty()) {
            ClearConsole.clear()
            println "Você não tem matches."
            println ""
        } else {
            vagas.forEach(vaga -> {
                println "${vaga.empresa} - ${vaga.vaga} - ${vaga.descricao} - ${vaga.local}"
            })
            println ""
        }
    }
}
