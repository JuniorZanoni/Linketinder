package Model

import Usuario.Vaga

class ModelVaga {
    List listarTodasVagasPorEmpresa(String email) {

        List vagas = []

        Connection.sql.query('''SELECT vagas.id, vagas.nome, vagas.descricao, vagas.local_vaga FROM vagas
                                        LEFT JOIN empresas ON vagas.id_empresa = empresas.id
                                        WHERE empresas.email = ?;''', [email]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String nome = resultSet.getString('nome').toString()
                String descricao = resultSet.getString('descricao').toString()
                String local = resultSet.getString('local_vaga').toString()
                vagas.add([id, nome, descricao, local])
            }
        }

        return vagas
    }

    List listarTodasVagasDisponiveisPorCandidato(String email) {

        List vagas = []

        Connection.sql.query('''SELECT * FROM vagas
                                        LEFT JOIN (SELECT * FROM curtidas_candidatos 
                                        WHERE id_candidato = (SELECT id FROM candidatos WHERE email = ?)) AS curtidas ON curtidas.id_vaga = vagas.id
                                        WHERE id_vaga IS NULL;''', [email]) { resultSet ->
            while (resultSet.next()) {
                Integer id = resultSet.getInt('id')
                String nome = resultSet.getString('nome')
                String descricao = resultSet.getString('descricao').toString()
                String local = resultSet.getString('local_vaga').toString()
                vagas.add([idVaga: id, nome: nome, descricao: descricao, local: local])
            }
        }

        return vagas
    }

    List listarTodosCandidatosDisponiveisPorVaga(Integer idVaga) {

        List vagas = []

        Connection.sql.query('''
                                        SELECT candidatos.descricao, candidatos.id FROM candidatos
                                        LEFT JOIN (SELECT * FROM curtidas_vagas WHERE id_vaga = ?) AS curtidas ON curtidas.id_candidato = candidatos.id
                                        WHERE id_candidato IS NULL;
                                ''', [idVaga]) { resultSet ->
            while (resultSet.next()) {
                Integer idCandidato = resultSet.getInt('id')
                String descricao = resultSet.getString('descricao').toString()
                vagas.add([descricao: descricao, idCandidato: idCandidato])
            }
        }

        return vagas
    }

    void cadastrar(Vaga vaga, String email) {
        Connection.sql.execute('''
                                    INSERT INTO vagas (id_empresa, nome, descricao, local_vaga)
                                    VALUES ((SELECT id FROM empresas WHERE email = ?),?,?,?)''',
                [
                        email,
                        vaga.nome,
                        vaga.descricao,
                        vaga.localVaga,
                ]
        )
    }

    Integer getVagaPorId(String email, Integer id) {
        Integer idVaga = 0

        Connection.sql.query('''SELECT vagas.id, vagas.nome, vagas.descricao, vagas.local_vaga FROM vagas
                                        LEFT JOIN empresas ON vagas.id_empresa = empresas.id
                                        WHERE empresas.email = ? AND vagas.id = ?;''', [email, id]) { resultSet ->
            resultSet.next()
            idVaga = resultSet.getInt('id')
        }

        return idVaga
    }

    void excluir(Integer idVaga) {
        Connection.sql.execute('''DELETE FROM vagas WHERE id = ?;''', [idVaga])
    }

    void atualizarNome(String valor, Integer idVaga) {
        Connection.sql.execute('''UPDATE vagas SET nome = ? WHERE id = ?;''', [valor, idVaga])
    }

    void atualizarDescricao(String valor, Integer idVaga) {
        Connection.sql.execute('''UPDATE vagas SET descricao = ? WHERE id = ?;''', [valor, idVaga])
    }

    void atualizarLocal(String valor, Integer idVaga) {
        Connection.sql.execute('''UPDATE vagas SET local_vaga = ? WHERE id = ?;''', [valor, idVaga])
    }

    Integer getIdUltimaVaga() {
        Integer idVaga = 0

        Connection.sql.query('''SELECT * FROM vagas ORDER BY id DESC LIMIT 1;''',) { resultSet ->
            resultSet.next()
            idVaga = resultSet.getInt('id')
        }

        return idVaga
    }
}
