package Service

class Empresa extends Usuario{
    String cnpj

    Empresa(String nome, String email, String pais, String cep, String descricao, String senha, String cnpj) {
        super(nome, email, pais, cep, descricao, senha)
        this.cnpj = cnpj
    }
}
