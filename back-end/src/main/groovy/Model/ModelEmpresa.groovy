package Model

import Service.Empresa

class ModelEmpresa {
    def connection

    ModelEmpresa(connection) {
        this.connection = connection
    }

    Empresa load(String email, String senha) {
        def empresa

        connection.sql.query('''SELECT * FROM empresas WHERE email = ? AND senha = ?;''', [email, senha]) { resultSet ->
            if (resultSet.next()) {
                String nome = resultSet.getString('nome').toString()
                String cnpj = resultSet.getString('cnpj').toString()
                String emailEmpresa = resultSet.getString('email').toString()
                String descricao = resultSet.getString('descricao').toString()
                String pais = resultSet.getString('pais').toString()
                String cep = resultSet.getString('cep').toString()
                String senhaEmpresa = resultSet.getString('senha').toString()

                empresa = new Empresa(nome, emailEmpresa, pais, cep, descricao, senhaEmpresa, cnpj)
            }
        }

        return empresa
    }

    boolean save(Empresa empresa) {
        connection.sql.execute('''
            INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha)
                            VALUES (?,?,?,?,?,?,?)''',
                [
                        empresa.nome,
                        empresa.cnpj,
                        empresa.email,
                        empresa.descricao,
                        empresa.pais,
                        empresa.cep,
                        empresa.senha
                ]
        )
    }

    void delete(Empresa empresa) {
        connection.sql.execute('''DELETE FROM empresas WHERE email = ?;''', [empresa.email])
    }

    void update(Empresa empresa) {
        println empresa.nome
        println empresa.cnpj
        println empresa.descricao
        println empresa.pais
        println empresa.cep
        println empresa.senha

        connection.sql.execute('''UPDATE empresas SET nome = ? WHERE email = ?''', [empresa.nome, empresa.email])
        connection.sql.execute('''UPDATE empresas SET cnpj = ? WHERE email = ?''', [empresa.cnpj, empresa.email])
        connection.sql.execute('''UPDATE empresas SET descricao = ? WHERE email = ?''', [empresa.descricao, empresa.email])
        connection.sql.execute('''UPDATE empresas SET pais = ? WHERE email = ?''', [empresa.pais, empresa.email])
        connection.sql.execute('''UPDATE empresas SET cep = ? WHERE email = ?''', [empresa.cep, empresa.email])
        connection.sql.execute('''UPDATE empresas SET senha = ? WHERE email = ?''', [empresa.senha, empresa.email])
    }

    void updateEmail(Empresa empresa, String email) {
        connection.sql.execute('''UPDATE empresas SET email = ? WHERE email = ?''', [email, empresa.email])
    }
}
