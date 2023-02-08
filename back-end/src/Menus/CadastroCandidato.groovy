package Menus

import Model.ModelCandidato
import Model.ModelCompetencia
import Usuario.Candidato
import Utils.Utils
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CadastroCandidato {

    void menu() {
        Utils.clearConsole()
        String primeiroNome = inputNome()
        String sobrenome = inputSobrenome()
        LocalDate dataDeNascimento = inputDataDeNascimento()
        String email = inputEmail()
        String cpf = inputCpf()
        String pais = inputPais()
        String cep = inputCep()
        String descricao = inputDescricao()
        String senha = inputSenha()

        Candidato candidato = new Candidato(primeiroNome, sobrenome, dataDeNascimento, email, cpf, pais, cep, descricao, senha)
        new ModelCandidato().cadastrar(candidato)

        Utils.clearConsole()
        adicionarCompetencia(candidato.email)

        MainMenu.menu()
    }

    private String inputNome() {
        String regex = /[A-Z][a-zéãíóúç]+/

        while (true) {
            Utils.clearConsole()
            println "Qual seu primeiro nome?"
            Scanner sc = new Scanner(System.in)
            String nome = sc.nextLine()

            if (nome.matches(regex)) {
                return nome
            }
        }
    }

    private String inputSobrenome() {
        String regex = /([A-Z][a-zéãíóúç]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Qual seu sobrenome?"
            Scanner sc = new Scanner(System.in)
            String sobrenome = sc.nextLine()

            if (sobrenome.matches(regex)) {
                return sobrenome
            }
        }
    }

    private LocalDate inputDataDeNascimento() {
        String regex = '^([0-2][0-9]|(3)[0-1])(/)(((0)[0-9])|((1)[0-2]))(/)\\d{4}$'

        while (true) {
            Utils.clearConsole()
            System.out.println("Qual sua data de nascimento? Utilize o formato dd/mm/yyyy.")
            Scanner sc = new Scanner(System.in)
            String data = sc.nextLine()

            if (data.matches(regex)) {
                return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            }
        }
    }

    private String inputEmail() {
        String regex = '^(.+)@(\\S+)\\.(.+)$'

        while (true) {
            Utils.clearConsole()
            System.out.println("Qual seu e-mail?")
            Scanner sc = new Scanner(System.in)
            String email = sc.nextLine()

            if (email.matches(regex)) {
                return email
            }
        }
    }

    private String inputCpf() {

        String regex = '[0-9]{11}'

        while (true) {
            Utils.clearConsole()
            println "Qual seu CPF? Digite apenas números."
            Scanner sc = new Scanner(System.in)
            String cpf = sc.nextLine()

            if (cpf.matches(regex)) {
                return cpf
            }
        }
    }

    private String inputPais() {
        String regex = /([A-Z][a-zéãíóúç]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Qual seu Pais?"
            Scanner sc = new Scanner(System.in)
            String pais = sc.nextLine()

            if (pais.matches(regex)) {
                return pais
            }
        }
    }

    private String inputCep() {

        String regex = "[0-9]{8}"

        while (true) {
            Utils.clearConsole()
            println "Qual seu CEP? Digite apenas números."
            Scanner sc = new Scanner(System.in)
            String cep = sc.nextLine()

            if (cep.matches(regex)) {
                return cep
            }
        }
    }

    private String inputDescricao() {
        Utils.clearConsole()
        println "Escreva uma descrição sobre você."
        Scanner sc = new Scanner(System.in)
        return sc.nextLine()
    }

    private String inputSenha() {
        String regex = '(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#_!\\-])[0-9a-zA-Z$*&@#_!-]{6,}'

        while (true) {
            Utils.clearConsole()
            System.out.println("Digite uma senha.")
            Scanner sc = new Scanner(System.in)
            String senha = sc.nextLine()

            if (senha.matches(regex)) {
                return senha
            }
        }
    }

    private void adicionarCompetencia(String email) {
        List competencias = new ModelCompetencia().getCompetencias()

        while (true) {
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
                    try {
                        new ModelCompetencia().adicionaCompetenciaUsuario(email, index)
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
}
