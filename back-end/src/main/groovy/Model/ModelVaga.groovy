package Model

import Service.Candidato
import Service.Empresa
import Service.Vaga

class ModelVaga {
    def connection

    ModelVaga(connection) {
        this.connection = connection
    }

    void save(Vaga vaga, Empresa empresa) {
        connection.sql.execute('''
                                    INSERT INTO vagas (id_empresa, nome, descricao, local_vaga)
                                    VALUES ((SELECT id FROM empresas WHERE email = ?),?,?,?)''',
                [
                        empresa.email,
                        vaga.nome,
                        vaga.descricao,
                        vaga.localVaga,
                ]
        )
    }

    Integer getIdLastVaga() {
        Integer idVaga = 0

        connection.sql.query('''SELECT * FROM vagas ORDER BY id DESC LIMIT 1;''',) { resultSet ->
            resultSet.next()
            idVaga = resultSet.getInt('id')
        }

        return idVaga
    }

    void saveCompetenciaVaga(Integer idVaga, Integer idCompetencia) {
        connection.sql.execute('''INSERT INTO vagas_competencias (id_vaga, id_competencia) VALUES (?, ?)''',
                [idVaga, idCompetencia])
    }

    List getAllVagasEmpresa(Empresa empresa) {

        List vagas = []

        connection.sql.query('''SELECT vagas.id, vagas.nome, vagas.descricao, vagas.local_vaga FROM vagas
                                        LEFT JOIN empresas ON vagas.id_empresa = empresas.id
                                        WHERE empresas.email = ?;''', [empresa.email]) { resultSet ->
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

    Integer getVagaId(Empresa empresa, Integer id) {
        Integer idVaga = 0

        connection.sql.query('''SELECT vagas.id, vagas.nome, vagas.descricao, vagas.local_vaga FROM vagas
                                        LEFT JOIN empresas ON vagas.id_empresa = empresas.id
                                        WHERE empresas.email = ? AND vagas.id = ?;''', [empresa.email, id]) { resultSet ->
            resultSet.next()
            idVaga = resultSet.getInt('id')
        }

        return idVaga
    }

    void atualizarNome(String value, Integer idVaga) {
        connection.sql.execute('''UPDATE vagas SET nome = ? WHERE id = ?;''', [value, idVaga])
    }

    void atualizarDescricao(String value, Integer idVaga) {
        connection.sql.execute('''UPDATE vagas SET descricao = ? WHERE id = ?;''', [value, idVaga])
    }

    void atualizarLocal(String value, Integer idVaga) {
        connection.sql.execute('''UPDATE vagas SET local_vaga = ? WHERE id = ?;''', [value, idVaga])
    }

    void delete(Integer idVaga) {
        connection.sql.execute('''DELETE FROM vagas WHERE id = ?;''', [idVaga])
    }

    void deleteCompetenciaVaga(Integer idVaga, Integer idCompetencia) {
        connection.sql.execute('''DELETE FROM vagas_competencias 
                                    WHERE id_vaga = ? AND id_competencia = ?;''',
                [idVaga, idCompetencia])
    }

    List getCompetenciasVaga(Integer idVaga) {

        List competencias = []

        connection.sql.query('''SELECT id_competencia, competencia FROM vagas_competencias
                                        LEFT JOIN competencias ON competencias.id = vagas_competencias.id_competencia
                                        WHERE id_vaga = ?;''', [idVaga]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id_competencia').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }

    List listarTodasVagasDisponiveisPorCandidato(Candidato candidato) {

        List vagas = []

        connection.sql.query('''SELECT * FROM vagas
                                        LEFT JOIN (SELECT * FROM curtidas_candidatos 
                                        WHERE id_candidato = (SELECT id FROM candidatos WHERE email = ?)) AS curtidas ON curtidas.id_vaga = vagas.id
                                        WHERE id_vaga IS NULL;''', [candidato.email]) { resultSet ->
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

    List listarTodasVagasPorEmpresa(String email) {

        List vagas = []

        connection.sql.query('''SELECT vagas.id, vagas.nome, vagas.descricao, vagas.local_vaga FROM vagas
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

    List listarTodosCandidatosDisponiveisPorVaga(Integer idVaga) {

        List vagas = []

        connection.sql.query('''
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

    Integer getVagaPorIdEmpresa(Empresa empresa, Integer id) {
        Integer idVaga = 0

        connection.sql.query('''SELECT vagas.id, vagas.nome, vagas.descricao, vagas.local_vaga FROM vagas
                                        LEFT JOIN empresas ON vagas.id_empresa = empresas.id
                                        WHERE empresas.email = ? AND vagas.id = ?;''', [empresa.email, id]) { resultSet ->
            resultSet.next()
            idVaga = resultSet.getInt('id')
        }

        return idVaga
    }
}
