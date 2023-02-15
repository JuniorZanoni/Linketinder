package Menus

import Model.ModelCandidato
import Model.ModelCurtidasCandidatos
import Model.ModelVaga
import Usuario.Candidato
import Utils.Utils

import java.time.LocalDate

class MenuCandidato {
    private Candidato candidato
    private String email

    MenuCandidato(Candidato candidato) {
        this.email = candidato.email
        this.candidato = candidato
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
                        new ModelCandidato().delete(email)
                        Utils.clearConsole()
                        menu()
                        break
                    case 2:
                        String nome = Utils.inputString(Utils.regexNome, "Digite o novo nome.")
                        candidato.nome = nome
                        candidato.update()
                        break
                    case 3:
                        String sobrenome = Utils.inputString(Utils.regexNome, "Digite o novo sobrenome.")
                        candidato.sobrenome = sobrenome
                        candidato.update()
                        break
                    case 4:
                        LocalDate dataDeNascimento = Utils.inputDate(Utils.regexDataDeNascimento, "Digite a nova data de nascimento.")
                        candidato.dataDeNascimento = dataDeNascimento
                        candidato.update()
                        break
                    case 5:
                        String email = Utils.inputString(Utils.regexNome, "Digite o novo email.")
                        candidato.email = email
                        candidato.update()
                        break
                    case 6:
                        String cpf = Utils.inputString(Utils.regexNome, "Digite o novo cpf.")
                        candidato.cpf = cpf
                        candidato.update()
                        break
                    case 7:
                        String pais = Utils.inputString(Utils.regexNome, "Digite o novo pais.")
                        candidato.pais = pais
                        candidato.update()
                        break
                    case 8:
                        String cep = Utils.inputString(Utils.regexNome, "Digite o novo cep.")
                        candidato.cep = cep
                        candidato.update()
                        break
                    case 9:
                        String descricao = Utils.inputString(Utils.regexNome, "Digite a nova descricao.")
                        candidato.descricao = descricao
                        candidato.update()
                        break
                    case 10:
                        String senha = Utils.inputString(Utils.regexNome, "Digite a nova senha.")
                        candidato.senha = senha
                        candidato.update()
                        break
                    case 11:
                        new MenuCompetencia(candidato).menuCompetencias()
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
        List vagas = new ModelCandidato().getMatches(email)

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
