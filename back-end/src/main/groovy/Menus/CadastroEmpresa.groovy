package Menus

import Usuario.Empresa
import Utils.Utils

class CadastroEmpresa {

    void menu() {
        Empresa empresa = createEmpresa()
        empresa.saveEmpresa()

        Utils.clearConsole()
        MainMenu.menu()
    }

    private Empresa createEmpresa() {
        Utils.clearConsole()
        String nome = Utils.inputString(Utils.regexNomeEmpresa, "Qual o nome da empresa?")
        String email = Utils.inputString(Utils.regexEmail, "Qual o e-mail da empresa?")
        String cnpj = Utils.inputString(Utils.regexCnpj, "Qual o CNPJ da empresa? Digite apenas números.")
        String pais = Utils.inputString(Utils.regexPais, "Qual o país da empresa?")
        String cep = Utils.inputString(Utils.regexCep, "Qual o CEP da empresa? Digite apenas números.")
        String descricao = Utils.inputString(Utils.regexDescricao, "Escreva uma descrição sobre a empresa.")
        String senha = Utils.inputString(Utils.regexSenha,"Digite uma senha.")

        return new Empresa(nome, email, cnpj, pais, cep, descricao, senha)
    }
}
