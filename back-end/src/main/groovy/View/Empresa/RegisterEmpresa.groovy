package View.Empresa

import Model.DBConnection
import Model.ModelEmpresa
import Service.Empresa
import Utils.Utils
import View.MainMenu

class RegisterEmpresa {
    void menu() {
        String nome = Utils.inputString(Utils.regexNomeEmpresa, "Digite o nome da empresa.")
        String cnpj = Utils.inputString(Utils.regexCnpj, "Digite o CNPJ da empresa.")
        String email = Utils.inputString(Utils.regexEmail, "Digite o e-mail da empresa.")
        String descricao = Utils.inputString(Utils.regexDescricao, "Digite a descrição da empresa.")
        String pais = Utils.inputString(Utils.regexPais, "Digite o pais da empresa.")
        String cep = Utils.inputString(Utils.regexCep, "Digite o CEP da empresa.")
        String senha = Utils.inputString(Utils.regexSenha, "Digite a senha da empresa.")

        Empresa empresa = new Empresa(nome, email, pais, cep, descricao, senha, cnpj)
        new ModelEmpresa(DBConnection).save(empresa)

        MainMenu.main()
    }
}
