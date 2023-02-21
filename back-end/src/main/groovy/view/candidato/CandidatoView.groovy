package view.candidato

import service.user.candidato.Candidato
import view.curtir.CurtirCandidatoView
import view.matches.MatchesCandidatoView

class CandidatoView {
    Candidato candidato

    CandidatoView(Candidato candidato) {
        this.candidato = candidato
    }

    void menu(){
        boolean condition = true
        while (condition) {
            println "1 - Editar conta."
            println "2 - Curtir."
            println "3 - Matches."
            println ""
            println "0 - Voltar."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "1":
                    new EditCandidato(candidato).menu()
                    break
                case "2":
                    new CurtirCandidatoView(candidato).menu()
                    break
                case "3":
                    new MatchesCandidatoView(candidato).menu()
                    break
                case "0":
                    condition = false
                    break
            }
        }
    }
}
