package View.Empresa

import Model.DBConnection
import Model.ModelEmpresa
import Service.Empresa
import Utils.Utils

import View.MainMenu

class EditEmpresa {
    Empresa empresa

    EditEmpresa(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        while (true) {
            Utils.clearConsole()
            println "1 - Apagar conta."
            println "2 - Editar nome."
            println "3 - Editar CNPJ."
            println "4 - Editar e-mail."
            println "5 - Editar descrição."
            println "6 - Editar país."
            println "7 - Editar CEP."
            println "8 - Editar senha."
            println ""
            println "0 - Voltar."

            Scanner sc = new Scanner(System.in)

            switch (sc.nextInt()) {
                case 1:
                    new ModelEmpresa(DBConnection).delete(empresa)
                    Utils.clearConsole()
                    MainMenu.main()
                    break
                case 2:
                    String nome = Utils.inputString(Utils.regexNomeEmpresa, "Digite o novo nome da empresa.")
                    empresa.nome = nome
                    new ModelEmpresa(DBConnection).update(empresa)
                    break
                case 3:
                    String cnpj = Utils.inputString(Utils.regexCnpj, "Digite o novo CNPJ da empresa.")
                    empresa.cnpj = cnpj
                    new ModelEmpresa(DBConnection).update(empresa)
                    break
                case 4:
                    String email = Utils.inputString(Utils.regexEmail, "Digite o novo e-mail da empresa.")
                    new ModelEmpresa(DBConnection).updateEmail(empresa, email)
                    empresa.email = email
                    break
                case 5:
                    String descricao = Utils.inputString(Utils.regexDescricao, "Digite a nova descricao da empresa.")
                    empresa.descricao = descricao
                    new ModelEmpresa(DBConnection).update(empresa)
                    break
                case 6:
                    String pais = Utils.inputString(Utils.regexPais, "Digite o novo pais da empresa.")
                    empresa.pais = pais
                    new ModelEmpresa(DBConnection).update(empresa)
                    break
                case 7:
                    String cep = Utils.inputString(Utils.regexCep, "Digite o novo CEP da empresa.")
                    empresa.cep = cep
                    new ModelEmpresa(DBConnection).update(empresa)
                    break
                case 8:
                    String senha = Utils.inputString(Utils.regexSenha, "Digite a nova senha da empresa.")
                    empresa.senha = senha
                    new ModelEmpresa(DBConnection).update(empresa)
                    break
                case 0:
                    Utils.clearConsole()
                    new MenuEmpresa(empresa).menu()
                    break
            }
        }
    }
}
