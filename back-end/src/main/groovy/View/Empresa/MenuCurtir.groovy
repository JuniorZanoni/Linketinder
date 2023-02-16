package View.Empresa

import Model.DBConnection
import Model.ModelCurtidasVagas
import Model.ModelVaga
import Service.Empresa
import Utils.Utils

class MenuCurtir {
    Empresa empresa

    MenuCurtir(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        List vagas = new ModelVaga(DBConnection).listarTodasVagasPorEmpresa(empresa.email)

        if (vagas.size() > 0) {
            while (true) {
                Utils.clearConsole()
                println "Digite o número da vaga que deseja curtir candidatos."
                vagas.forEach { vaga ->
                    {
                        println "${vaga[0]} - ${vaga[1]} - ${vaga[2]} - ${vaga[3]}"
                    }
                }

                Scanner sc = new Scanner(System.in)

                try {
                    Integer idVaga = new ModelVaga(DBConnection).getVagaId(empresa, sc.nextInt())

                    if (idVaga) {
                        Utils.clearConsole()
                        curtir(idVaga)
                    }
                } catch (Exception e) {
                }
            }
        } else {
            println "Não existem vagas cadastradas."
            println ""
            new MenuEmpresa(empresa).menu()
        }
    }

    private void curtir(Integer idVaga) {
        List candidatos = new ModelVaga(DBConnection).listarTodosCandidatosDisponiveisPorVaga(idVaga)

        if (candidatos.size() > 0) {
            candidatos.forEach(candidato -> {
                Boolean condicao = true
                while (condicao) {
                    Utils.clearConsole()
                    println "Descrição: ${candidato.descricao}"
                    println ""
                    println "1 - Gostei."
                    println "2 - Não gostei."

                    Scanner sc = new Scanner(System.in)

                    try {
                        switch (sc.nextInt()) {
                            case 1:
                                new ModelCurtidasVagas(DBConnection).curtiCandidato(idVaga, candidato.idCandidato)
                                if (new ModelCurtidasVagas(DBConnection).match(idVaga, candidato.idCandidato)) {
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
            Utils.clearConsole()
            new MenuEmpresa(empresa).menu()
        } else {
            println "Você não tem candidatos para curtir nessa vaga, tente mais tarde."
            println ""
            new MenuVaga(empresa).menu()
        }
    }
}
