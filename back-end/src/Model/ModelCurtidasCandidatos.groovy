package Model

class ModelCurtidasCandidatos {
    void curtiVaga(Integer idVaga, String email) {
        Connection.sql.execute('''
                                    INSERT INTO curtidas_candidatos (id_vaga, id_candidato) 
                                        VALUES (?, (SELECT id FROM candidatos WHERE email = ?))
                               ''',
                [idVaga, email]
        )
    }

    boolean match(Integer idVaga, String email) {
        boolean match = false

        Connection.sql.query('''
                    SELECT * FROM curtidas_vagas WHERE id_vaga = ? AND id_candidato = (SELECT id FROM candidatos WHERE email = ?)
                    ''', [idVaga, email]) { resultSet ->

            if (resultSet.next()) {
                Connection.sql.execute('''
                    INSERT INTO matchs (id_vaga, id_candidato) VALUES (?, (SELECT id FROM candidatos WHERE email = ?))
                    ''', [idVaga, email])

                match = true
            }
        }
        return match
    }
}
