package model

import groovy.sql.Sql
import service.user.candidato.Candidato

class ModelMatch {
    Sql connection

    ModelMatch(connection) {
        this.connection = connection
    }

    List getMatchesCandidato(Candidato candidato) {
        List matches = []

        connection.query('''
                                    SELECT vagas.id, empresas.nome AS empresa, vagas.nome AS vaga, vagas.descricao, vagas.local_vaga FROM matchs
                                        LEFT JOIN candidatos ON candidatos.id = matchs.id_candidato
                                        LEFT JOIN vagas ON vagas.id = matchs.id_vaga
                                        LEFT JOIN empresas ON empresas.id = vagas.id_empresa
                                        WHERE candidatos.email = ?;
        ''', [candidato.email]) { resultSet ->
            while (resultSet.next()) {
                Integer idVaga = resultSet.getInt('id')
                String empresa = resultSet.getString('empresa').toString()
                String vaga = resultSet.getString('vaga').toString()
                String descricao = resultSet.getString('descricao').toString()
                String local = resultSet.getString('local_vaga').toString()
                matches.add([idVaga: idVaga, empresa: empresa, vaga: vaga, descricao: descricao, local: local])
            }
        }

        return matches
    }

    List getMatchesEmpresa(Integer idVaga) {
        List vagas = []

        connection.query('''
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
