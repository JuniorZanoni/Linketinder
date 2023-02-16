package View.Empresa

import Model.DBConnection
import Model.ModelMatches
import Model.ModelVaga
import Service.Empresa
import Utils.Utils

class MenuMatches {

    Empresa empresa

    MenuMatches(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        List vagas = new ModelVaga(DBConnection).listarTodasVagasPorEmpresa(empresa.email)

        if (vagas.size() > 0) {
            while (true) {
                Utils.clearConsole()
                println "Digite o número da vaga que deseja ver os matches."
                vagas.forEach { vaga ->
                    {
                        println "${vaga[0]} - ${vaga[1]} - ${vaga[2]} - ${vaga[3]}"
                    }
                }

                Scanner sc = new Scanner(System.in)

                try {
                    Integer idVaga = new ModelVaga(DBConnection).getVagaPorIdEmpresa(empresa, sc.nextInt())

                    if (idVaga) {
                        Utils.clearConsole()
                        matches(idVaga)
                    }
                } catch (Exception e) {
                }
            }
        } else {
            println "Não existem vagas cadastradas."
            println ""
            new MenuVaga(empresa).menu()
        }
    }

    private void matches(Integer idVaga) {
        Utils.clearConsole()
        List candidatos = new ModelMatches(DBConnection).getMatchesEmpresa(idVaga)

        if (candidatos.size() > 0) {
            candidatos.forEach(candidato -> {
                println "${candidato.nome} - ${candidato.email} - ${candidato.descricao}"
            })

            println ""
            new MenuVaga(empresa).menu()
        } else {
            Utils.clearConsole()
            println "Você não tem matches nessa vaga."
            println ""
            new MenuVaga(empresa).menu()
        }
    }
}
