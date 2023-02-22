package model

import model.DBConnection
import service.user.Empresa
import groovy.sql.Sql

class DAOEmpresa {
    Sql sql = Sql.newInstance(DBConnection.getDBConnection())

    boolean save(Empresa empresa) {
        return sql.execute('''
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
        sql.execute('''DELETE FROM empresas WHERE email = ?;''', [empresa.email])
    }

    void update(Empresa empresa, Integer idEmpresa) {
        sql.execute('''UPDATE empresas SET nome = ? WHERE id = ?''', [empresa.nome, idEmpresa])
        sql.execute('''UPDATE empresas SET cnpj = ? WHERE id = ?''', [empresa.cnpj, idEmpresa])
        sql.execute('''UPDATE empresas SET email = ? WHERE id = ?''', [empresa.email, idEmpresa])
        sql.execute('''UPDATE empresas SET descricao = ? WHERE id = ?''', [empresa.descricao, idEmpresa])
        sql.execute('''UPDATE empresas SET pais = ? WHERE id = ?''', [empresa.pais, idEmpresa])
        sql.execute('''UPDATE empresas SET cep = ? WHERE id = ?''', [empresa.cep, idEmpresa])
        sql.execute('''UPDATE empresas SET senha = ? WHERE id = ?''', [empresa.senha, idEmpresa])
    }

    Empresa getEmpresa(String email, String senha) {
        Empresa empresa = null

        sql.query('''SELECT * FROM empresas WHERE email = ? AND senha = ?;''', [email, senha]) { resultSet ->
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
        Integer id = null
        sql.query('''
            SELECT id FROM empresas WHERE email = ?;''', [empresa.email]
        ) {resultSet ->
            resultSet.next()
            id = resultSet.getInt('id')
        }

        return id
    }
}
