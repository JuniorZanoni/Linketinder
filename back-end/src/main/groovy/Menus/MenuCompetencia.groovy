package Menus

import Model.ModelCompetencia
import Usuario.Candidato
import Usuario.Competencia
import Utils.Utils

class MenuCompetencia {
    Candidato candidato

    MenuCompetencia(Candidato candidato) {
        this.candidato = candidato
    }

    void menuCompetencias() {
        boolean condition = true
        while (condition) {
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
                    case 0:
                        condition = false
                        break
                }
            } catch (Exception e) {
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
                new ModelCompetencia().save(competencia)
                break
            }
        }
    }

    private void adicionarCompetencia() {
        Utils.clearConsole()
        while (true) {
            Competencia competencia = new Competencia()

            println "Digite o número da competência que deseja adicionar."
            competencia.printCompetencias()

            Scanner sc = new Scanner(System.in)

            try {
                competencia.addCompetencia(candidato, sc.nextInt())
                break
            } catch (Exception e) {
                Utils.clearConsole()
                println "Você já possui essa competência."
                println ""
            }
        }
    }

    private void removerCompetencia() {
        while (true) {
            Competencia competencia = new Competencia()

            Utils.clearConsole()
            println "Digite o número da competência que deseja remover."

            List competencias = competencia.getCompetenciasUsuario(candidato)
            competencias.forEach { e -> println "${e[0]} - ${e[1]}"}

            Scanner sc = new Scanner(System.in)

            try {
                competencia.removeCompetencia(candidato, sc.nextInt())
                break
            } catch (Exception e) {
            }
        }
    }

    private void listarCompetencias() {
        Utils.clearConsole()
        Competencia competencia = new Competencia()
        List competencias = competencia.getCompetenciasUsuario(candidato)
        competencias.forEach { e -> println "${e[0]} - ${e[1]}"}
        println ""
        println "Pressione enter para voltar."
        Scanner sc = new Scanner(System.in)
        sc.nextLine()
    }

}
