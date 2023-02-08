package Menus

import Model.ModelCandidato
import Model.ModelCompetencia
import Model.ModelCurtidasCandidatos
import Model.ModelVaga
import Utils.Utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MenuCandidato {
    private String email

    MenuCandidato(String email) {
        this.email = email
    }

    void menu() {
        while (true) {
            println "1 - Editar conta."
            println "2 - Curtir vagas."
            println "3 - Ver matches."
            println "4 - Voltar."

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        menuEditar()
                        break
                    case 2:
                        menuCurtir()
                        break
                    case 3:
                        menuMatches()
                        break
                    case 4:
                        MainMenu.main()
                        break
                }
            } catch (Exception e) {
            }
        }
    }

    private void menuEditar() {
        while (true) {
            Utils.clearConsole()
            println "1 - Apagar conta."
            println "2 - Editar nome."
            println "3 - Editar sobrenome."
            println "4 - Editar data de nascimento."
            println "5 - Editar e-mail."
            println "6 - Editar CPF."
            println "7 - Editar país."
            println "8 - Editar CEP."
            println "9 - Editar descrição."
            println "10 - Editar senha."
            println "11 - Editar competências."
            println "12 - Voltar."

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        new ModelCandidato().apagar(email)
                        Utils.clearConsole()
                        menu()
                        break
                    case 2:
                        editarNome()
                        break
                    case 3:
                        editarSobrenome()
                        break
                    case 4:
                        editarDataDeNascimento()
                        break
                    case 5:
                        editarEmail()
                        break
                    case 6:
                        editarCpf()
                        break
                    case 7:
                        editarPais()
                        break
                    case 8:
                        editarCep()
                        break
                    case 9:
                        editarDescricao()
                        break
                    case 10:
                        editarSenha()
                        break
                    case 11:
                        menuCompetencias()
                        break
                    case 12:
                        Utils.clearConsole()
                        menu()
                        break
                }
            } catch (Exception e) {
            }
        }
    }

    private void menuCompetencias() {
        while (true) {
            Utils.clearConsole()
            println "1 - Criar competência."
            println "2 - Adicionar competência."
            println "3 - Remover competência."
            println "4 - Listar competências."
            println "9 - Voltar."

            Scanner sc = new Scanner(System.in)

            try {
                switch (sc.nextInt()) {
                    case 1:
                        cadastrarCompetencia()
                        break
                    case 2:
                        adicionarCompetencia()
                        break
                    case 3:
                        removerCompetencia()
                        break
                    case 4:
                        listarCompetencias()
                        break
                    case 9:
                        menuEditar()
                        break
                }
            } catch (Exception e) {
            }
        }
    }

    private void editarNome() {
        String regex = /[A-Z][a-zéãíóúç]+/

        while (true) {
            Utils.clearConsole()
            println "Digite o novo nome."
            Scanner sc = new Scanner(System.in)
            String nome = sc.nextLine()

            if (nome.matches(regex)) {
                new ModelCandidato().atualizarNome(nome, email)
                menuEditar()
                break
            }
        }
    }

    private void editarSobrenome() {
        String regex = /([A-Z][a-zéãíóúç]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Digite o novo sobrenome."
            Scanner sc = new Scanner(System.in)
            String sobrenome = sc.nextLine()

            if (sobrenome.matches(regex)) {
                new ModelCandidato().atualizarSobrenome(sobrenome, email)
                menuEditar()
                break
            }
        }
    }

    private void editarDataDeNascimento() {
        String regex = '^([0-2][0-9]|(3)[0-1])(/)(((0)[0-9])|((1)[0-2]))(/)\\d{4}$'

        while (true) {
            Utils.clearConsole()
            System.out.println("Digite a nova data de nascimento no formato dd/mm/aaaa.")
            Scanner sc = new Scanner(System.in)
            String data = sc.nextLine()

            if (data.matches(regex)) {
                new ModelCandidato().atualizarDataDeNascimento(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")), email)
                menuEditar()
                break
            }
        }
    }

    private void editarEmail() {
        String regex = '^(.+)@(\\S+)\\.(.+)$'

        while (true) {
            Utils.clearConsole()
            System.out.println("Digite seu novo e-mail.")
            Scanner sc = new Scanner(System.in)
            String email = sc.nextLine()

            if (email.matches(regex)) {
                new ModelCandidato().atualizarEmail(email, this.email)
                this.email = email
                menuEditar()
                break
            }
        }
    }

    private void editarCpf() {

        String regex = '[0-9]{11}'

        while (true) {
            Utils.clearConsole()
            println "Digite o novo CPF. Digite apenas números."
            Scanner sc = new Scanner(System.in)
            String cpf = sc.nextLine()

            if (cpf.matches(regex)) {
                new ModelCandidato().atualizarCpf(cpf, this.email)
                menuEditar()
                break
            }
        }
    }

    private void editarPais() {
        String regex = /([A-Z][a-zéãíóúç]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Qual o novo país."
            Scanner sc = new Scanner(System.in)
            String pais = sc.nextLine()

            if (pais.matches(regex)) {
                new ModelCandidato().atualizarPais(pais, this.email)
                menuEditar()
                break
            }
        }
    }

    private void editarCep() {

        String regex = "[0-9]{8}"

        while (true) {
            Utils.clearConsole()
            println "Digite o novo CEP"
            Scanner sc = new Scanner(System.in)
            String cep = sc.nextLine()

            if (cep.matches(regex)) {
                new ModelCandidato().atualizarCep(cep, this.email)
                menuEditar()
                break
            }
        }
    }

    private void editarDescricao() {
        Utils.clearConsole()
        println "Digite a nova descrição."
        Scanner sc = new Scanner(System.in)

        new ModelCandidato().atualizarDescricao(sc.nextLine(), this.email)
        menuEditar()
    }

    private void editarSenha() {
        String regex = '(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#_!\\-])[0-9a-zA-Z$*&@#_!-]{6,}'

        while (true) {
            Utils.clearConsole()
            System.out.println("Digite a nova senha.")
            Scanner sc = new Scanner(System.in)
            String senha = sc.nextLine()

            if (senha.matches(regex)) {
                new ModelCandidato().atualizarSenha(senha, this.email)
                menuEditar()
                break
            }
        }
    }

    private void cadastrarCompetencia() {
        String regex = /([A-Za-zéãíóúç]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Digite a nova competência."
            Scanner sc = new Scanner(System.in)
            String competencia = sc.nextLine()

            if (competencia.matches(regex)) {
                new ModelCompetencia().cadastrar(competencia)
                menuCompetencias()
                break
            }
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

            Scanner sc = new Scanner(System.in)

            try {
                new ModelCompetencia().adicionaCompetenciaUsuario(email, sc.nextInt())
                menuCompetencias()
            } catch (Exception e) {
            }
        }
    }

    private void removerCompetencia() {
        List competencias = new ModelCompetencia().getCompetenciasUsuario(email)

        while (true) {
            Utils.clearConsole()
            println "Digite o número da competência que deseja remover."

            competencias.forEach { competencia ->
                println "${competencia[0]} - ${competencia[1]}"
            }

            Scanner sc = new Scanner(System.in)

            try {
                new ModelCompetencia().removerCompetenciaUsuario(sc.nextInt(), email)
                menuCompetencias()
            } catch (Exception e) {
            }
        }
    }

    private void listarCompetencias() {
        Utils.clearConsole()
        List competencias = new ModelCompetencia().getCompetenciasUsuario(email)
        competencias.forEach { competencia ->
            println "${competencia[0]} - ${competencia[1]}"
        }
        println ""
        println "Pressione enter para voltar."
        Scanner sc = new Scanner(System.in)
        sc.nextLine()
        menuCompetencias()
    }

    private void menuCurtir() {
        List vagas = new ModelVaga().listarTodasVagasDisponiveisPorCandidato(email)

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
                                new ModelCurtidasCandidatos().curtiVaga((Integer) vaga.idVaga, this.email)

                                if (new ModelCurtidasCandidatos().match((Integer) vaga.idVaga, this.email)) {
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
            menu()
        }
        Utils.clearConsole()
    }

    private void menuMatches() {
        Utils.clearConsole()
        List vagas = new ModelCandidato().matches(email)

        if (vagas.size() > 0) {
            vagas.forEach(vaga -> {
                println "${vaga.empresa} - ${vaga.vaga} - ${vaga.descricao} - ${vaga.local}"
            })

            println ""
            menu()
        } else {
            Utils.clearConsole()
            println "Você não tem matches."
            println ""
            menu()
        }

    }
}
