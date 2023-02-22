package model.curtida

import groovy.sql.Sql
import model.DBConnection

class DAOCurtidasVagas {
    Sql sql = Sql.newInstance(DBConnection.getDBConnection())

    boolean curtiCandidato(Integer idVaga, Integer idCandidato) {
        sql.execute('''
                                    INSERT INTO curtidas_vagas (id_vaga, id_candidato) VALUES (?, ?)
                               ''',
                [idVaga, idCandidato]
        )
    }

    boolean match(Integer idVaga, Integer idCandidato) {
        boolean match = false

        sql.query('''
                    SELECT * FROM curtidas_candidatos WHERE id_vaga = ? AND id_candidato = ?
                    ''', [idVaga, idCandidato]) { resultSet ->

            if (resultSet.next()) {
                sql.execute('''
                    INSERT INTO matchs (id_vaga, id_candidato) VALUES (?, ?)
                    ''', [idVaga, idCandidato])

                match = true
            }
        }
        return match
    }

}
