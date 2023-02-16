package View.Empresa

import Model.DBConnection
import Model.ModelVaga
import Service.Empresa
import Utils.Utils

class MenuListVagas {
    Empresa empresa

    MenuListVagas(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        List vagas = new ModelVaga(DBConnection).listarTodasVagasPorEmpresa(empresa.email)
        if (vagas.size() > 0) {
            Utils.clearConsole()
            vagas.forEach { vaga ->
                {
                    println "${vaga[1]} - ${vaga[2]} - ${vaga[3]}"
                }
            }
            println ""
            new MenuVaga(empresa).menu()
        } else {
            Utils.clearConsole()
            println "Não existem vagas cadastradas."
            println ""
            new MenuVaga(empresa).menu()
        }
    }
}
