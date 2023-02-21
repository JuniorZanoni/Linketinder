package view.register

import model.DBConnection
import model.ModelEmpresa
import service.user.empresa.Empresa
import utils.service.Regex
import utils.view.Input

class RegisterEmpresa {

    static void menu() {
        String name = Input.create(Regex.nomeEmpresa, "Digite o nome da empresa.")
        String email = new Input().create(Regex.email, "Digite o email da empresa.")
        String cnpj = new Input().create(Regex.cnpj, "Digite o seu CNPJ da empresa.")
        String description = new Input().create(Regex.descricao, "Digite a descrição da empresa.")
        String country = new Input().create(Regex.pais, "Digite o pais da empresa.")
        String cep = new Input().create(Regex.cep, "Digite o CEP da empresa.")
        String password = new Input().create(Regex.senha, "Digite a sua password.")

        Empresa empresa = new Empresa(name, email, cnpj, description, country, cep, password)
        new ModelEmpresa(DBConnection.sql).save(empresa)
    }

}
