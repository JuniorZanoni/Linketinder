package Model

import Usuario.Empresa
import org.codehaus.groovy.util.ListHashMap

class ModelEmpresa {

    void save(ListHashMap empresa) {
        Connection.sql.execute('''
            INSERT INTO empresas (nome, email, cnpj, pais, cep, descricao, senha)
                            VALUES (?,?,?,?,?,?,?)''',
                [
                        empresa.nome,
                        empresa.email,
                        empresa.cnpj,
                        empresa.pais,
                        empresa.cep,
                        empresa.descricao,
                        empresa.senha
                ]
        )
    }

    Empresa get(String email, String senha) {
        def empresa

        Connection.sql.query('''SELECT * FROM empresas WHERE email = ? AND senha = ?;''', [email, senha]) { resultSet ->
            if (resultSet.next()) {
                String nome = resultSet.getString('nome').toString()
                String cnpj = resultSet.getString('cnpj').toString()
                String emailEmpresa = resultSet.getString('email').toString()
                String descricao = resultSet.getString('descricao').toString()
                String pais = resultSet.getString('pais').toString()
                String cep = resultSet.getString('cep').toString()
                String senhaEmpresa = resultSet.getString('senha').toString()

                empresa = new Empresa(nome, emailEmpresa, cnpj, pais, cep, descricao, senhaEmpresa)
            }
        }

        return empresa
    }

    void apagar(String email) {
        Connection.sql.execute('''DELETE FROM empresas WHERE email = ?;''', [email])
    }

    void update(ListHashMap empresa) {
        Connection.sql.execute('''UPDATE empresas SET nome = ? WHERE email = ?''', [empresa.nome, empresa.email])
        Connection.sql.execute('''UPDATE empresas SET cnpj = ? WHERE email = ?''', [empresa.cnpj, empresa.email])
        Connection.sql.execute('''UPDATE empresas SET email = ? WHERE email = ?''', [empresa.email, empresa.email])
        Connection.sql.execute('''UPDATE empresas SET descricao = ? WHERE email = ?''', [empresa.descricao, empresa.email])
        Connection.sql.execute('''UPDATE empresas SET pais = ? WHERE email = ?''', [empresa.pais, empresa.email])
        Connection.sql.execute('''UPDATE empresas SET cep = ? WHERE email = ?''', [empresa.cep, empresa.email])
        Connection.sql.execute('''UPDATE empresas SET senha = ? WHERE email = ?''', [empresa.senha, empresa.email])
    }

    List matches(Integer idVaga) {
        List vagas = []

        Connection.sql.query('''
                                    SELECT candidatos.nome, candidatos.email, candidatos.descricao FROM matchs
                                        LEFT JOIN candidatos ON candidatos.id = matchs.id_candidato
                                        WHERE id_vaga = ?;;
        ''', [idVaga]) { resultSet ->
            while (resultSet.next()) {
                String nome = resultSet.getString('nome').toString()
                String email = resultSet.getString('email').toString()
                String descricao = resultSet.getString('descricao').toString()
                vagas.add([nome: nome, email: email, descricao: descricao])
            }
        }

        return vagas
    }
}