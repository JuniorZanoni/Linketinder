package view.matches

import model.DBConnection
import model.ModelEmpresa
import model.ModelMatch
import model.ModelVaga
import service.user.empresa.Empresa
import utils.view.ClearConsole

class MatchesVagaView {
    Empresa empresa
    Integer idEmpresa = new ModelEmpresa(DBConnection.sql).getId(empresa)

    MatchesVagaView (Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        List vagas = new ModelVaga(DBConnection.sql).getAllVagasByEmpresaWithID(idEmpresa)

        if (vagas.isEmpty()) {
            println "Não existem vagas cadastradas."
            println ""
        } else {
            boolean condition = true
            while (condition) {
                ClearConsole.clear()
                println "Digite o número da vaga que deseja ver os matches."
                vagas.forEach {println "${it[0]} - ${it[1]} - ${it[2]} - ${it[3]}"}

                Scanner sc = new Scanner(System.in)

                try {
                    Integer idVaga = sc.nextInt()
                    boolean verifyVaga = new ModelVaga(DBConnection.sql).verifyVagaID(empresa, idVaga)

                    if (verifyVaga) {
                        ClearConsole.clear()
                        matches(idVaga)
                        condition = false
                    }
                } catch (Exception e) {}
            }
        }
    }

    private void matches(Integer idVaga) {
        ClearConsole.clear()
        List candidatos = new ModelMatch(DBConnection.sql).getMatchesEmpresa(idVaga)

        if (candidatos.size() > 0) {
            candidatos.forEach(candidato -> {
                println "${candidato.nome} - ${candidato.email} - ${candidato.descricao}"
            })

            println ""
        } else {
            ClearConsole.clear()
            println "Você não tem matches nessa vaga."
            println ""
        }
    }

}
