package model.modelCurtidas

import groovy.sql.Sql
import service.user.Candidato

import java.sql.Connection

class ModelCurtidasCandidatos {
    Sql sql

    ModelCurtidasCandidatos(Connection connection) {
        this.sql = Sql.newInstance(connection)
    }

    void curtiVaga(Integer idVaga, Candidato candidato) {
        sql.execute('''
                                    INSERT INTO curtidas_candidatos (id_vaga, id_candidato) 
                                        VALUES (?, (SELECT id FROM candidatos WHERE email = ?))
                               ''',
                [idVaga, candidato.email]
        )
    }

    boolean match(Integer idVaga, Candidato candidato) {
        boolean match = false

        sql.query('''
                    SELECT * FROM curtidas_vagas WHERE id_vaga = ? AND id_candidato = (SELECT id FROM candidatos WHERE email = ?)
                    ''', [idVaga, candidato.email]) { resultSet ->

            if (resultSet.next()) {
                sql.execute('''
                    INSERT INTO matchs (id_vaga, id_candidato) VALUES (?, (SELECT id FROM candidatos WHERE email = ?))
                    ''', [idVaga, candidato.email])

                match = true
            }
        }
        return match
    }
}
