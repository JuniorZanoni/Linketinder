package View.Empresa

import Model.DBConnection
import Model.ModelCompetencia
import Model.ModelVaga
import Service.Empresa
import Service.Vaga
import Utils.Utils

class MenuCreateVaga {

    Empresa empresa

    MenuCreateVaga(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        String nome = Utils.inputString(Utils.regexNomeEmpresa, "Digite o nome da vaga.")
        String descricao = Utils.inputString(Utils.regexNomeEmpresa, "Digite o nome a descrição.")
        String local = Utils.inputString(Utils.regexNomeEmpresa, "Digite o nome o local da vaga.")

        Vaga vaga = new Vaga(nome, descricao, local)
        new ModelVaga(DBConnection).save(vaga, empresa)

        addCompetenciaVaga()

        Utils.clearConsole()
    }

    private void addCompetenciaVaga() {
        List competencias = new ModelCompetencia(DBConnection).loadAllCompetencias()
        Utils.clearConsole()

        while (true) {
            println "Digite o número da competência que deseja adicionar."

            competencias.forEach { competencia ->
                println "${competencia[0]} - ${competencia[1]}"
            }
            println ""
            println "0 - Continuar para o menu."

            Scanner sc = new Scanner(System.in)

            Integer index = sc.nextInt()

            try {
                if (index == 0) {
                    Utils.clearConsole()
                    new MenuVaga(empresa).menu()
                    break
                } else {
                    Integer idUltimaVaga = new ModelVaga(DBConnection).getIdLastVaga()
                    new ModelVaga(DBConnection).saveCompetenciaVaga(idUltimaVaga, index)
                    competencias.remove(competencias.find(competencia -> competencia[0] == index.toString()))
                    Utils.clearConsole()
                }
            } catch (Exception e) {
                Utils.clearConsole()
                println "Competência inválida"
                println ""
            }
        }
    }
}
