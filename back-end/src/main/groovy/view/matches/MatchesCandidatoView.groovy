package view.matches

import controller.ControllerMatch
import service.user.Candidato
import utils.view.ClearConsole

class MatchesCandidatoView {
    Candidato candidato

    MatchesCandidatoView(Candidato candidato) {
        this.candidato = candidato
    }

    void menu() {
        ClearConsole.clear()
        List<Map> vagas = ControllerMatch.getMatchesCandidato(candidato)

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
