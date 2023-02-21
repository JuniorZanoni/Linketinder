package view.curtir

import model.DBConnection
import model.ModelVaga
import model.modelCurtidas.ModelCurtidasCandidatos
import service.user.candidato.Candidato
import utils.view.ClearConsole

class CurtirCandidatoView {
    Candidato candidato

    CurtirCandidatoView(Candidato candidato) {
        this.candidato = candidato
    }

    void menu() {
        List vagas = new ModelVaga(DBConnection.sql).listarTodasVagasDisponiveisPorCandidato(candidato)

        if(vagas.isEmpty()) {
            ClearConsole.clear()
            println "Você não tem vagas para curtir, tente mais tarde."
            println ""
        } else {
            vagas.forEach(vaga -> {
                boolean condicao = true
                while (condicao) {
                    ClearConsole.clear()
                    println "Nome: ${vaga.nome}"
                    println "Descrição: ${vaga.descricao}"
                    println "Local: ${vaga.local}"
                    println ""
                    println "1 - Gostei."
                    println "2 - Não gostei."

                    Scanner sc = new Scanner(System.in)
                    String option = sc.nextLine()

                    switch (option) {
                        case "1":
                            new ModelCurtidasCandidatos(DBConnection.sql).curtiVaga((Integer) vaga.idVaga, candidato)
                            Boolean match = verifyMatch(new ModelCurtidasCandidatos(DBConnection.sql).match((Integer) vaga.idVaga, candidato))
                            verifyMatch(match)
                            condicao = false
                            ClearConsole.clear()
                            break
                        case "2":
                            condicao = false
                            ClearConsole.clear()
                            break
                    }
                }
            })
        }
    }

    private verifyMatch(Boolean match) {
        if (match) {
            println ""
            println "MATCH!"
            println ""
            println "Pressione enter para continuar."
            Scanner sc2 = new Scanner(System.in)
            sc2.nextLine()
        }
    }
}
