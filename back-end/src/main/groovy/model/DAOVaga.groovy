package model

import groovy.sql.Sql
import service.user.Candidato
import service.user.Empresa
import service.vaga.Vaga

class DAOVaga {
    Sql sql = Sql.newInstance(DBConnection.getDBConnection())

    Integer getId(Vaga vaga, Integer idEmpresa) {
        Integer idVaga = null

        sql.query('''SELECT id FROM vagas WHERE id_empresa = ? AND nome = ? AND descricao = ?;''',
                [idEmpresa, vaga.name, vaga.description]
        ) {resultSet ->
            resultSet.next()
            idVaga = resultSet.getInt('id')
        }

        return idVaga
    }

    List<Vaga> getVagasByEmpresa(Integer idEmpresa) {
        List<Vaga> vagas = []

        sql.query('''SELECT vagas.nome, vagas.descricao, vagas.local_vaga FROM vagas
                                LEFT JOIN empresas ON vagas.id_empresa = empresas.id
                                WHERE empresas.id = ?;''', [idEmpresa]) { resultSet ->
            while (resultSet.next()) {
                String nome = resultSet.getString('nome')
                String descricao = resultSet.getString('descricao')
                String local = resultSet.getString('local_vaga')

                Vaga vaga = new Vaga(nome, descricao, local)

                vagas.add(vaga)
            }
        }

        return vagas
    }

    Integer save(Vaga vaga, Integer idEmpresa) {
        sql.execute('''INSERT INTO vagas (id_empresa, nome, descricao, local_vaga) VALUES (?,?,?,?)''',
                [idEmpresa, vaga.name, vaga.description, vaga.local]
        )

        return getId(vaga, idEmpresa)
    }

    void update(Vaga vaga, Integer idVaga) {
        sql.execute('''UPDATE vagas SET nome = ? WHERE id = ?;''', [vaga.name, idVaga])
        sql.execute('''UPDATE vagas SET descricao = ? WHERE id = ?;''', [vaga.description, idVaga])
        sql.execute('''UPDATE vagas SET local_vaga = ? WHERE id = ?;''', [vaga.local, idVaga])
    }

    void delete(Integer idVaga) {
        sql.execute('''DELETE FROM vagas WHERE id = ?;''', [idVaga])
    }

    List<Map> getVagasByCandidato(Candidato candidato) {

        List<Map> vagas = []

        sql.query('''SELECT * FROM vagas
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

    List getCandidatosAvailableByVaga(Integer idVaga) {

        List vagas = []

        sql.query('''
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

    List<Map> getVagasByEmpresaWithID(Integer idEmpresa) {

        List<Map> vagas = []

        sql.query('''SELECT vagas.id, vagas.nome, vagas.descricao, vagas.local_vaga FROM vagas
                                        LEFT JOIN empresas ON vagas.id_empresa = empresas.id
                                        WHERE empresas.id = ?;''', [idEmpresa]) { resultSet ->
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

    String verifyVagaID(Empresa empresa, Integer idVaga) {
        sql.firstRow('''SELECT vagas.id FROM vagas
                                LEFT JOIN empresas ON vagas.id_empresa = empresas.id
                                WHERE empresas.email = ? AND vagas.id = ?''', [empresa.email, idVaga])
    }
}
