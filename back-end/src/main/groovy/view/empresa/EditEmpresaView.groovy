package view.empresa

import controller.ControllerEmpresa
import service.user.Empresa
import utils.service.Regex
import utils.view.ClearConsole
import utils.view.Input
import view.MainMenu

class EditEmpresaView {
    Empresa empresa
    Integer idEmpresa = ControllerEmpresa.getId(empresa)

    EditEmpresaView(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        boolean condition = true
        while (condition) {
            ClearConsole.clear()
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

            switch (sc.nextLine()) {
                case "1":
                    ControllerEmpresa.delete(empresa)
                    ClearConsole.clear()
                    MainMenu.main()
                    break
                case "2":
                    String name = Input.create(Regex.nomeEmpresa, "Digite o novo nome da empresa.")
                    empresa.nome = name
                    ControllerEmpresa.update(empresa, idEmpresa)
                    break
                case "3":
                    String cnpj = Input.create(Regex.cnpj, "Digite o novo CNPJ da empresa.")
                    empresa.cnpj = cnpj
                    ControllerEmpresa.update(empresa, idEmpresa)
                    break
                case "4":
                    String email = Input.create(Regex.email, "Digite o novo e-mail da empresa.")
                    empresa.email = email
                    ControllerEmpresa.update(empresa, idEmpresa)
                    break
                case "5":
                    String description = Input.create(Regex.descricao, "Digite o nova descrição da empresa.")
                    empresa.descricao = description
                    ControllerEmpresa.update(empresa, idEmpresa)
                    break
                case "6":
                    String country = Input.create(Regex.pais, "Digite o novo país da empresa.")
                    empresa.pais = country
                    ControllerEmpresa.update(empresa, idEmpresa)
                    break
                case "7":
                    String cep = Input.create(Regex.cep, "Digite o novo CEP da empresa.")
                    empresa.cep = cep
                    ControllerEmpresa.update(empresa, idEmpresa)
                    break
                case "8":
                    String password = Input.create(Regex.senha, "Digite o nova senha da empresa.")
                    empresa.senha = password
                    ControllerEmpresa.update(empresa, idEmpresa)
                    break
                case "0":
                    ClearConsole.clear()
                    condition = false
                    break
            }
        }
    }
}
