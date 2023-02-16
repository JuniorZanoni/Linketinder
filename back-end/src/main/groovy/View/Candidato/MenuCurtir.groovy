package View.Candidato

import Model.DBConnection
import Model.ModelCurtidasCandidatos
import Model.ModelVaga
import Service.Candidato
import Utils.Utils

class MenuCurtir {
    Candidato candidato

    MenuCurtir(Candidato candidato) {
        this.candidato = candidato
    }

    void menu() {
        List vagas = new ModelVaga(DBConnection).listarTodasVagasDisponiveisPorCandidato(candidato)

        if (vagas.size() > 0) {
            vagas.forEach(vaga -> {
                Boolean condicao = true
                while (condicao) {
                    Utils.clearConsole()
                    println "Nome: ${vaga.nome}"
                    println "Descrição: ${vaga.descricao}"
                    println "Local: ${vaga.local}"
                    println ""
                    println "1 - Gostei."
                    println "2 - Não gostei."

                    Scanner sc = new Scanner(System.in)

                    try {
                        switch (sc.nextInt()) {
                            case 1:
                                new ModelCurtidasCandidatos(DBConnection).curtiVaga((Integer) vaga.idVaga, candidato)

                                if (new ModelCurtidasCandidatos(DBConnection).match((Integer) vaga.idVaga, candidato)) {
                                    println ""
                                    println "MATCH!"
                                    println ""
                                    println "Pressione enter para continuar."
                                    Scanner sc2 = new Scanner(System.in)
                                    sc2.nextLine()
                                }

                                condicao = false
                                break
                            case 2:
                                condicao = false
                                break
                        }
                    } catch (Exception e) {
                    }
                }
            })
        } else {
            Utils.clearConsole()
            println "Você não tem vagas para curtir, tente mais tarde."
            println ""
            new MenuCandidato(candidato).menu()
        }
        Utils.clearConsole()
    }
}
