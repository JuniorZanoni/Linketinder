package model.modelCurtidas

import groovy.sql.Sql
import service.user.candidato.Candidato

class ModelCurtidasCandidatos {
    Sql connection

    ModelCurtidasCandidatos(Sql connection) {
        this.connection = connection
    }

    void curtiVaga(Integer idVaga, Candidato candidato) {
        connection.execute('''
                                    INSERT INTO curtidas_candidatos (id_vaga, id_candidato) 
                                        VALUES (?, (SELECT id FROM candidatos WHERE email = ?))
                               ''',
                [idVaga, candidato.email]
        )
    }

    boolean match(Integer idVaga, Candidato candidato) {
        boolean match = false

        connection.query('''
                    SELECT * FROM curtidas_vagas WHERE id_vaga = ? AND id_candidato = (SELECT id FROM candidatos WHERE email = ?)
                    ''', [idVaga, candidato.email]) { resultSet ->

            if (resultSet.next()) {
                connection.execute('''
                    INSERT INTO matchs (id_vaga, id_candidato) VALUES (?, (SELECT id FROM candidatos WHERE email = ?))
                    ''', [idVaga, candidato.email])

                match = true
            }
        }
        return match
    }
}
