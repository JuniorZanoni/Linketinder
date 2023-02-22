package model.curtida

import groovy.sql.Sql
import model.DBConnection
import service.user.Candidato

class DAOCurtidasCandidatos {
    Sql sql = Sql.newInstance(DBConnection.getDBConnection())

    void curtirVaga(Integer idVaga, Candidato candidato) {
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
