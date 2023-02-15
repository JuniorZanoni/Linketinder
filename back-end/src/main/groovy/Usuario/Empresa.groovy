package Usuario

import Model.ModelCandidato
import Model.ModelEmpresa
import org.codehaus.groovy.util.ListHashMap

class Empresa {
    String nome, email, cnpj, pais, cep, descricao, senha

    Empresa(String nome, String email, String cnpj, String pais, String cep, String descricao, String senha) {
        this.nome = nome
        this.email = email
        this.cnpj = cnpj
        this.pais = pais
        this.cep = cep
        this.descricao = descricao
        this.senha = senha
    }

    void saveEmpresa() {
        new ModelEmpresa().save(infosEmpresa())
    }

    void update() {
        new ModelEmpresa().update(infosEmpresa())
    }

    private ListHashMap infosEmpresa() {
        return [
                nome: this.nome,
                email: this.email,
                cnpj: this.cnpj,
                pais: this.pais,
                cep: this.cep,
                descricao: this.descricao,
                senha: this.senha
        ]
    }
}
