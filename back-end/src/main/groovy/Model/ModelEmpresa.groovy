package Model

import Usuario.Empresa

class ModelEmpresa {

    void cadastrar(Empresa empresa) {
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

    String getByEmailAndPass(String email, String senha) {

        String emailRetorno = ""

        Connection.sql.query('''SELECT * FROM empresas WHERE email = ? AND senha = ?;''', [email, senha]) { resultSet ->
            if (resultSet.next()) {
                emailRetorno = resultSet.getString('email').toString()
            }
        }

        return emailRetorno
    }

    void apagar(String email) {
        Connection.sql.execute('''DELETE FROM empresas WHERE email = ?;''', [email])
    }

    void atualizarNome(String novoValor, String email) {
        Connection.sql.execute('''UPDATE empresas SET nome = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarCnpj(String novoValor, String email) {
        Connection.sql.execute('''UPDATE empresas SET cnpj = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarEmail(String novoValor, String email) {
        Connection.sql.execute('''UPDATE empresas SET email = ? WHERE email = ?;''', [novoValor, email])
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