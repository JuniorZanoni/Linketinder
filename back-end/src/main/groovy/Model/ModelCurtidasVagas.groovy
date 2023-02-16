package Model

class ModelCurtidasVagas {
    def connection

    ModelCurtidasVagas(connection) {
        this.connection = connection
    }

    boolean curtiCandidato(Integer idVaga, Integer idCandidato) {
        connection.sql.execute('''
                                    INSERT INTO curtidas_vagas (id_vaga, id_candidato) VALUES (?, ?)
                               ''',
                [idVaga, idCandidato]
        )
    }

    boolean match(Integer idVaga, Integer idCandidato) {
        boolean match = false

        connection.sql.query('''
                    SELECT * FROM curtidas_candidatos WHERE id_vaga = ? AND id_candidato = ?
                    ''', [idVaga, idCandidato]) { resultSet ->

            if (resultSet.next()) {
                connection.sql.execute('''
                    INSERT INTO matchs (id_vaga, id_candidato) VALUES (?, ?)
                    ''', [idVaga, idCandidato])

                match = true
            }
        }
        return match
    }

}
