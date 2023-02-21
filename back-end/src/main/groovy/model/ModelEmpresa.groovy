package model

import service.user.empresa.Empresa
import groovy.sql.Sql

class ModelEmpresa {
    Sql connection

    ModelEmpresa(Sql connection) {
        this.connection = connection
    }

    Empresa getEmpresa(String email, String senha) {
        Empresa empresa = null

        connection.query('''SELECT * FROM empresas WHERE email = ? AND senha = ?;''', [email, senha]) { resultSet ->
            if (resultSet.next()) {
                String nome = resultSet.getString('nome')
                String emailEmpresa = resultSet.getString('email')
                String cnpj = resultSet.getString('cnpj')
                String pais = resultSet.getString('pais')
                String cep = resultSet.getString('cep')
                String descricao = resultSet.getString('descricao')
                String senhaEmpresa = resultSet.getString('senha')

                empresa = new Empresa(nome, emailEmpresa, cnpj, descricao, pais, cep, senhaEmpresa)
            }
        }

        return empresa
    }

    Integer getId(Empresa empresa) {
        Integer id = 0
        connection.query('''
            SELECT id FROM empresas WHERE email = ?;''', [empresa.email]
        ) {resultSet ->
            resultSet.next()
            id = resultSet.getInt('id')
        }

        return id
    }

    void save(Empresa empresa) {
        connection.execute('''
            INSERT INTO empresas (nome, cnpj, email, description, pais, cep, password)
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
        connection.execute('''DELETE FROM empresas WHERE email = ?;''', [empresa.email])
    }

    void update(Empresa empresa, Integer idEmpresa) {
        connection.execute('''UPDATE empresas SET nome = ? WHERE id = ?''', [empresa.nome, idEmpresa])
        connection.execute('''UPDATE empresas SET cnpj = ? WHERE id = ?''', [empresa.cnpj, idEmpresa])
        connection.execute('''UPDATE empresas SET email = ? WHERE id = ?''', [empresa.email, idEmpresa])
        connection.execute('''UPDATE empresas SET descricao = ? WHERE id = ?''', [empresa.descricao, idEmpresa])
        connection.execute('''UPDATE empresas SET pais = ? WHERE id = ?''', [empresa.pais, idEmpresa])
        connection.execute('''UPDATE empresas SET cep = ? WHERE id = ?''', [empresa.cep, idEmpresa])
        connection.execute('''UPDATE empresas SET senha = ? WHERE id = ?''', [empresa.senha, idEmpresa])
    }
}
