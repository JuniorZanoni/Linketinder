package Menus

import Model.ModelCandidato
import Model.ModelCompetencia
import Model.ModelCurtidasCandidatos
import Model.ModelCurtidasVagas
import Model.ModelEmpresa
import Model.ModelVaga
import Usuario.Vaga
import Utils.Utils

class MenuEmpresa {
    private String email

    MenuEmpresa(String email) {
        this.email = email
    }

    void menu() {
        while (true) {
            println "1 - Vagas."
            println "2 - Editar empresa."
            println "3 - Voltar."

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        Utils.clearConsole()
                        menuVagas()
                        break
                    case 2:
                        Utils.clearConsole()
                        editarEmpresa()
                        break
                    case 3:
                        Utils.clearConsole()
                        MainMenu.main()
                        break
                }
            } catch (Exception e) {
            }
        }
    }

    private void menuVagas() {
        println "1 - Criar vaga."
        println "2 - Listar vagas."
        println "3 - Editar vagas."
        println "4 - Curtir candidatos."
        println "5 - Ver matches."
        println "6 - Voltar."

        Scanner sc = new Scanner(System.in)

        try {
            switch (sc.nextInt()) {
                case 1:
                    Utils.clearConsole()
                    criarVaga()
                    break
                case 2:
                    Utils.clearConsole()
                    listarVagas()
                    break
                case 3:
                    Utils.clearConsole()
                    menuEditarVaga()
                    break
                case 4:
                    Utils.clearConsole()
                    menuCurtir()
                    break
                case 5:
                    Utils.clearConsole()
                    menuMatches()
                    break
                case 6:
                    Utils.clearConsole()
                    menu()
                    break
            }
        } catch (Exception e) {
        }
    }

    private void criarVaga() {
        String nome = inputNome()
        String descricao = inputDescricao()
        String local = inputLocal()

        Vaga vaga = new Vaga(nome, descricao, local)
        println new ModelVaga().cadastrar(vaga, this.email)

        adicionarCompetencia()

        Utils.clearConsole()
    }

    private void listarVagas() {
        List vagas = new ModelVaga().listarTodasVagasPorEmpresa(this.email)
        if (vagas.size() > 0) {
            Utils.clearConsole()
            vagas.forEach { vaga ->
                {
                    println "${vaga[1]} - ${vaga[2]} - ${vaga[3]}"
                }
            }
            println ""
            menuVagas()
        } else {
            Utils.clearConsole()
            println "Não existem vagas cadastradas."
            println ""
            menuVagas()
        }

    }

    private String inputNome() {
        String regex = /([A-Za-zéãíóúç-]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Qual o nome da vaga?"
            Scanner sc = new Scanner(System.in)
            String nome = sc.nextLine()

            if (nome.matches(regex)) {
                return nome
            }
        }
    }

    private String inputDescricao() {
        String regex = /([A-Za-zéãíóúç-]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Qual a descrição da vaga?"
            Scanner sc = new Scanner(System.in)
            String nome = sc.nextLine()

            if (nome.matches(regex)) {
                return nome
            }
        }
    }

    private String inputLocal() {
        String regex = /([A-Za-zéãíóúç-]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Qual o local da vaga?"
            Scanner sc = new Scanner(System.in)
            String local = sc.nextLine()

            if (local.matches(regex)) {
                return local
            }
        }
    }

    private void menuEditarVaga() {
        List vagas = new ModelVaga().listarTodasVagasPorEmpresa(this.email)

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
                    Integer idVaga = new ModelVaga().getVagaPorId(this.email, sc.nextInt())

                    if (idVaga) {
                        Utils.clearConsole()
                        editarVaga(idVaga)
                    }
                } catch (Exception e) {
                }
            }
        } else {
            println "Não existem vagas cadastradas."
            println ""
            menuVagas()
        }

    }

    private void editarVaga(Integer idVaga) {
        while (true) {
            println "1 - Editar nome."
            println "2 - Editar descrição."
            println "3 - Editar local."
            println "4 - Editar competências."
            println "5 - Excluir vaga."
            println "6 - Voltar"

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        Utils.clearConsole()
                        String novoNome = inputEditarNome()
                        new ModelVaga().atualizarNome(novoNome, idVaga)
                        Utils.clearConsole()
                        editarVaga(idVaga)
                        break
                    case 2:
                        Utils.clearConsole()
                        String novadescricao = inputEditarDescricao()
                        new ModelVaga().atualizarDescricao(novadescricao, idVaga)
                        Utils.clearConsole()
                        editarVaga(idVaga)
                        break
                    case 3:
                        Utils.clearConsole()
                        String local = inputEditarLocal()
                        new ModelVaga().atualizarLocal(local, idVaga)
                        Utils.clearConsole()
                        editarVaga(idVaga)
                        break
                    case 4:
                        Utils.clearConsole()
                        menuCompetencias(idVaga)
                        break
                    case 5:
                        Utils.clearConsole()
                        excluirVaga(idVaga)
                        Utils.clearConsole()
                        menuVagas()
                        break
                    case 6:
                        Utils.clearConsole()
                        menuVagas()
                        break
                }
            } catch (Exception e) {
            }
        }
    }

    private String inputEditarNome() {
        String regex = /([A-Za-zéãíóúç-]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Digite o novo nome da vaga."
            Scanner sc = new Scanner(System.in)
            String nome = sc.nextLine()

            if (nome.matches(regex)) {
                return nome
            }
        }
    }

    private String inputEditarDescricao() {
        String regex = /([A-Za-zéãíóúç-]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Digite a nova descrição da vaga."
            Scanner sc = new Scanner(System.in)
            String descricao = sc.nextLine()

            if (descricao.matches(regex)) {
                return descricao
            }
        }
    }

    private String inputEditarLocal() {
        String regex = /([A-Za-zéãíóúç-]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Digite o novo local da vaga."
            Scanner sc = new Scanner(System.in)
            String nome = sc.nextLine()

            if (nome.matches(regex)) {
                return nome
            }
        }
    }

    private void excluirVaga(Integer idVaga) {
        new ModelVaga().excluir(idVaga)
    }

    private void menuCompetencias(Integer idVaga) {
        while (true) {
            Utils.clearConsole()
            println "1 - Criar competência."
            println "2 - Adicionar competência."
            println "3 - Remover competência."
            println "4 - Listar competências."
            println "5 - Voltar."

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
                    case 5:
                        Utils.clearConsole()
                        menuVagas()
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
                new ModelCompetencia().cadastrar(competencia)
                menuCompetencias(idVaga)
                break
            }
        }
    }

    private void adicionarCompetencia(Integer idVaga) {
        List competencias = new ModelCompetencia().getCompetencias()

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
                        new ModelCompetencia().adicionaCompetenciaVaga(idVaga, index)
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
        List competencias = new ModelCompetencia().getCompetenciasVaga(idVaga)

        while (true) {
            Utils.clearConsole()
            println "Digite o número da competência que deseja remover."

            competencias.forEach { competencia ->
                println "${competencia[0]} - ${competencia[1]}"
            }

            Scanner sc = new Scanner(System.in)

            try {
                new ModelCompetencia().removerCompetenciaVaga(idVaga, sc.nextInt())
                menuCompetencias(idVaga)
            } catch (Exception e) {
            }
        }
    }

    private void listarCompetencias(Integer idVaga) {
        Utils.clearConsole()
        List competencias = new ModelCompetencia().getCompetenciasVaga(idVaga)
        competencias.forEach { competencia ->
            println "${competencia[0]} - ${competencia[1]}"
        }
        println ""
        println "Pressione enter para voltar."
        Scanner sc = new Scanner(System.in)
        sc.nextLine()
        menuCompetencias(idVaga)
    }

    private void editarEmpresa() {

        while (true) {
            Utils.clearConsole()
            println "1 - Apagar conta."
            println "2 - Editar nome."
            println "3 - Editar CNPJ."
            println "4 - Editar e-mail."
            println "5 - Voltar."

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        new ModelEmpresa().apagar(this.email)
                        Utils.clearConsole()
                        MainMenu.menu()
                        break
                    case 2:
                        editarNome()
                        editarEmpresa()
                        break
                    case 3:
                        editarCnpj()
                        editarEmpresa()
                        break
                    case 4:
                        editarEmail()
                        editarEmpresa()
                        break
                    case 5:
                        Utils.clearConsole()
                        menu()
                        break

                }
            } catch (Exception e) {
            }
        }
    }

    private void editarNome() {
        String regex = /[A-Za-z0-9éãíóúç\-õ\s]+/

        while (true) {
            Utils.clearConsole()
            println "Digite o novo nome da empresa."
            Scanner sc = new Scanner(System.in)
            String nome = sc.nextLine()

            if (nome.matches(regex)) {
                new ModelEmpresa().atualizarNome(nome, this.email)
                editarEmpresa(this.email)
                break
            }
        }
    }

    private void editarCnpj() {
        String regex = '[0-9]{14}'

        while (true) {
            Utils.clearConsole()
            println "Digite o novo CNPJ da empresa."
            Scanner sc = new Scanner(System.in)
            String cnpj = sc.nextLine()

            if (cnpj.matches(regex)) {
                new ModelEmpresa().atualizarCnpj(cnpj, this.email)
                editarEmpresa(this.email)
                break
            }
        }
    }

    private void editarEmail() {
        String regex = '^(.+)@(\\S+)\\.(.+)$'

        while (true) {
            Utils.clearConsole()
            System.out.println("Digite o novo e-mail da empresa.")
            Scanner sc = new Scanner(System.in)
            String novoEmail = sc.nextLine()

            if (novoEmail.matches(regex)) {
                new ModelEmpresa().atualizarEmail(novoEmail, this.email)
                this.email = novoEmail
                editarEmpresa(this.email)
                break
            }
        }
    }

    private void menuCurtir() {
        List vagas = new ModelVaga().listarTodasVagasPorEmpresa(this.email)

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
                    Integer idVaga = new ModelVaga().getVagaPorId(this.email, sc.nextInt())

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
            menuVagas()
        }
    }

    private void curtir(Integer idVaga) {
        List candidatos = new ModelVaga().listarTodosCandidatosDisponiveisPorVaga(idVaga)

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
                                new ModelCurtidasVagas().curtiCandidato(idVaga, candidato.idCandidato)
                                if (new ModelCurtidasVagas().match(idVaga, candidato.idCandidato)) {
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
            menuVagas()
        } else {
            println "Você não tem candidatos para curtir nessa vaga, tente mais tarde."
            println ""
            menu()
        }
    }

    private void adicionarCompetencia() {
        List competencias = new ModelCompetencia().getCompetencias()

        while (true) {
            Utils.clearConsole()
            println "Digite o número da competência que deseja adicionar."

            competencias.forEach { competencia ->
                println "${competencia[0]} - ${competencia[1]}"
            }
            println ""
            println "0 - Continuar para o menu."

            Scanner sc = new Scanner(System.in)

            try {
                Integer index = sc.nextInt()

                if (index == 0) {
                    Utils.clearConsole()
                    break
                } else {
                    Integer idUltimaVaga = new ModelVaga().getIdUltimaVaga()
                    new ModelCompetencia().adicionaCompetenciaVaga(idUltimaVaga, index)
                    competencias.remove(competencias.find(competencia -> competencia[0] == index.toString()))
                }
            } catch (Exception e) {
            }
        }
    }

    private void menuMatches() {
        List vagas = new ModelVaga().listarTodasVagasPorEmpresa(this.email)

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
                    Integer idVaga = new ModelVaga().getVagaPorId(this.email, sc.nextInt())

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
            menuVagas()
        }
    }

    private void matches(Integer idVaga) {
        Utils.clearConsole()
        List candidatos = new ModelEmpresa().matches(idVaga)

        if (candidatos.size() > 0) {
            candidatos.forEach(candidato -> {
                println "${candidato.nome} - ${candidato.email} - ${candidato.descricao}"
            })

            println ""
            menuVagas()
        } else {
            Utils.clearConsole()
            println "Você não tem matches nessa vaga."
            println ""
            menuVagas()
        }
    }
}
