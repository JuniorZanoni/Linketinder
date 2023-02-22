package view.curtir

import controller.ControllerCurtir
import controller.ControllerEmpresa
import controller.ControllerMatch
import controller.ControllerVaga
import service.user.Empresa
import utils.view.ClearConsole

class CurtirVagaView {
    Empresa empresa
    Integer idEmpresa = ControllerEmpresa.getId(empresa)

    CurtirVagaView(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        List vagas = ControllerVaga.getAllVagasByEmpresaWithID(idEmpresa)

        if (vagas.isEmpty()) {
            ClearConsole.clear()
            println "Não existem vagas cadastradas."
            println ""
        } else {
            boolean condition = true
            while (condition) {
                ClearConsole.clear()
                println "Digite o número da vaga que deseja curtir candidatos."
                println ""
                vagas.forEach { println "${it[0]} - ${it[1]} - ${it[2]} - ${it[3]}" }

                Scanner sc = new Scanner(System.in)

                try {
                    Integer idVaga = sc.nextInt()
                    boolean verifyVaga = ControllerVaga.verifyVagaID(empresa, idVaga)

                    if (verifyVaga) {
                        ClearConsole.clear()
                        curtir(idVaga)
                        condition = false
                    }
                } catch (Exception ignored) {}
            }
        }
    }

    private void curtir(Integer idVaga) {
        List<Map> candidatos = ControllerVaga.getCandidatosAvailableByVaga(idVaga)

        if (candidatos.isEmpty()) {
            println "Você não tem candidatos para curtir nessa vaga, tente mais tarde."
            println ""
        } else {
            candidatos.forEach(candidato -> {
                Boolean condicao = true
                while (condicao) {
                    ClearConsole.clear()
                    println "Descrição: ${candidato.descricao}"
                    println ""
                    println "1 - Gostei."
                    println "2 - Não gostei."

                    Scanner sc = new Scanner(System.in)
                    String option = sc.nextLine()

                    switch (option) {
                        case "1":
                            ControllerCurtir.curtirCandidato(idVaga, candidato.idCandidato)
                            if (ControllerMatch.matchVagaWithCandidato(idVaga, candidato.idCandidato)) {
                                println ""
                                println "MATCH!"
                                println ""
                                println "Pressione enter para continuar."
                                Scanner sc2 = new Scanner(System.in)
                                sc2.nextLine()
                            }

                            condicao = false
                            break
                        case "2":
                            condicao = false
                            break
                    }
                }
            })
            ClearConsole.clear()
        }
    }
}
