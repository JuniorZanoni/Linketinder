package Menus

import Model.ModelEmpresa
import Usuario.Empresa
import Utils.Utils

class CadastroEmpresa {

    void menu() {
        Utils.clearConsole()
        String nome = inputNome()
        String email = inputEmail()
        String cnpj = inputCnpj()
        String pais = inputPais()
        String cep = inputCep()
        String descricao = inputDescricao()
        String senha = inputSenha()

        Empresa empresa = new Empresa(nome, email, cnpj, pais, cep, descricao, senha)

        new ModelEmpresa().cadastrar(empresa)

        Utils.clearConsole()
        MainMenu.menu()
    }

    String inputNome() {
        String regex = /[A-Za-z0-9éãíóúç\-õ\s]+/

        while (true) {
            Utils.clearConsole()
            println "Qual o nome da empresa?"
            Scanner sc = new Scanner(System.in)
            String nome = sc.nextLine()

            if (nome.matches(regex)) {
                return nome
            }
        }
    }

    String inputEmail() {
        String regex = '^(.+)@(\\S+)\\.(.+)$'

        while (true) {
            Utils.clearConsole()
            System.out.println("Qual o e-mail da empresa?")
            Scanner sc = new Scanner(System.in)
            String email = sc.nextLine()

            if (email.matches(regex)) {
                return email
            }
        }
    }

    String inputCnpj() {

        String regex = '[0-9]{14}'

        while (true) {
            Utils.clearConsole()
            println "Qual o CNPJ da empresa? Digite apenas números."
            Scanner sc = new Scanner(System.in)
            String cpf = sc.nextLine()

            if (cpf.matches(regex)) {
                return cpf
            }
        }
    }

    String inputPais() {
        String regex = /([A-Z][a-zéãíóúç]+\s?)+/

        while (true) {
            Utils.clearConsole()
            println "Qual o país da empresa?"
            Scanner sc = new Scanner(System.in)
            String pais = sc.nextLine()

            if (pais.matches(regex)) {
                return pais
            }
        }
    }

    String inputCep() {

        String regex = "[0-9]{8}"

        while (true) {
            Utils.clearConsole()
            println "Qual o CEP da empresa? Digite apenas números."
            Scanner sc = new Scanner(System.in)
            String cep = sc.nextLine()

            if (cep.matches(regex)) {
                return cep
            }
        }
    }

    String inputDescricao() {
        Utils.clearConsole()
        println "Escreva uma descrição sobre a empresa."
        Scanner sc = new Scanner(System.in)
        return sc.nextLine()
    }

    String inputSenha() {
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
}
