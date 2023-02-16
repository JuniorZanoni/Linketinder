package View.Empresa

import Model.DBConnection
import Model.ModelCompetencia
import Model.ModelVaga
import Service.Empresa
import Utils.Utils

class MenuEditVaga {

    Empresa empresa

    MenuEditVaga(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        List vagas = new ModelVaga(DBConnection).getAllVagasEmpresa(empresa)

        if (vagas.size() > 0) {
            while (true) {
                Utils.clearConsole()
                println "Digite o número da vaga que deseja editar."
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
                        editVaga(idVaga)
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

    private void editVaga(Integer idVaga) {
        while (true) {
            println "1 - Editar nome."
            println "2 - Editar descrição."
            println "3 - Editar local."
            println "4 - Editar competências."
            println "5 - Excluir vaga."
            println ""
            println "0 - Voltar"

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        Utils.clearConsole()
                        String name = Utils.inputString(Utils.regexNomeEmpresa, "Digite o novo nome da vaga.")
                        new ModelVaga(DBConnection).atualizarNome(name, idVaga)
                        Utils.clearConsole()
                        editVaga(idVaga)
                        break
                    case 2:
                        Utils.clearConsole()
                        String novadescricao = Utils.inputString(Utils.regexNomeEmpresa, "Digite a nova descrição da vaga.")
                        new ModelVaga(DBConnection).atualizarDescricao(novadescricao, idVaga)
                        Utils.clearConsole()
                        editVaga(idVaga)
                        break
                    case 3:
                        Utils.clearConsole()
                        String local = Utils.inputString(Utils.regexNomeEmpresa, "Digite o novo local da vaga.")
                        new ModelVaga(DBConnection).atualizarLocal(local, idVaga)
                        Utils.clearConsole()
                        editVaga(idVaga)
                        break
                    case 4:
                        Utils.clearConsole()
                        menuCompetencias(idVaga)
                        break
                    case 5:
                        Utils.clearConsole()
                        deleteVaga(idVaga)
                        Utils.clearConsole()
                        new MenuVaga(empresa).menu()
                        break
                    case 0:
                        Utils.clearConsole()
                        new MenuVaga(empresa).menu()
                        break
                }
            } catch (Exception e) {
            }
        }
    }

    private void menuCompetencias(Integer idVaga) {
        while (true) {
            Utils.clearConsole()
            println "1 - Criar competência."
            println "2 - Adicionar competência."
            println "3 - Remover competência."
            println "4 - Listar competências."
            println "0 - Voltar."

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        cadastrarCompetencia(idVaga)
                        break
                    case 2:
                        Utils.clearConsole()
                        adicionarCompetencia(idVaga)
                        break
                    case 3:
                        removerCompetencia(idVaga)
                        break
                    case 4:
                        listarCompetencias(idVaga)
                        break
                    case 0:
                        Utils.clearConsole()
                        new MenuVaga(empresa).menu()
                        break
                }
            } catch (Exception e) {
            }
        }
    }

    private void cadastrarCompetencia(Integer idVaga) {
        String regex = /([A-Za-zéãíóúç]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Digite a nova competência."
            Scanner sc = new Scanner(System.in)
            String competencia = sc.nextLine()

            if (competencia.matches(regex)) {
                new ModelCompetencia(DBConnection).saveCompetencia(competencia)
                menuCompetencias(idVaga)
                break
            }
        }
    }

    private void adicionarCompetencia(Integer idVaga) {
        List competencias = new ModelCompetencia(DBConnection).loadAllCompetencias()

        while (true) {
            println "Digite o número da competência que deseja adicionar."

            competencias.forEach { competencia ->
                println "${competencia[0]} - ${competencia[1]}"
            }

            println ""
            println "0 - Voltar."

            Scanner sc = new Scanner(System.in)

            try {
                Integer index = sc.nextInt()

                if (index == 0) {
                    Utils.clearConsole()
                    break
                } else {
                    try {
                        new ModelVaga(DBConnection).saveCompetenciaVaga(idVaga, index)
                        competencias.remove(competencias.find(competencia -> competencia[0] == index.toString()))
                        Utils.clearConsole()
                    } catch (Exception e) {
                        Utils.clearConsole()
                        println "Você já possui essa competência."
                        println ""
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    private void removerCompetencia(Integer idVaga) {
        List competencias = new ModelCompetencia(DBConnection).loadAllCompetencias()

        while (true) {
            Utils.clearConsole()
            println "Digite o número da competência que deseja remover."

            competencias.forEach { competencia ->
                println "${competencia[0]} - ${competencia[1]}"
            }

            Scanner sc = new Scanner(System.in)

            try {
                new ModelVaga(DBConnection).deleteCompetenciaVaga(idVaga, sc.nextInt())
                menuCompetencias(idVaga)
            } catch (Exception e) {
            }
        }
    }

    private void listarCompetencias(Integer idVaga) {
        Utils.clearConsole()
        List competencias = new ModelVaga(DBConnection).getCompetenciasVaga(idVaga)
        competencias.forEach { competencia ->
            println "${competencia[0]} - ${competencia[1]}"
        }
        println ""
        println "Pressione enter para voltar."
        Scanner sc = new Scanner(System.in)
        sc.nextLine()
        menuCompetencias(idVaga)
    }

    private void deleteVaga(Integer idVaga) {
        new ModelVaga(DBConnection).delete(idVaga)
    }
}
