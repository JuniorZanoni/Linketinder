package view.register

import controller.ConstrollerEmpresa
import utils.service.Regex
import utils.view.Input

class RegisterEmpresaView {

    static void menu() {
        String name = Input.create(Regex.nomeEmpresa, "Digite o nome da empresa.")
        String email = new Input().create(Regex.email, "Digite o email da empresa.")
        String cnpj = new Input().create(Regex.cnpj, "Digite o seu CNPJ da empresa.")
        String description = new Input().create(Regex.descricao, "Digite a descrição da empresa.")
        String country = new Input().create(Regex.pais, "Digite o pais da empresa.")
        String cep = new Input().create(Regex.cep, "Digite o CEP da empresa.")
        String password = new Input().create(Regex.senha, "Digite a sua password.")

        Map<String, String> empresaBO = [
                name: name,
                email: email,
                cnpj: cnpj,
                country: country,
                cep: cep,
                description: description,
                password: password
        ]

        new ConstrollerEmpresa().save(empresaBO)
    }
}
