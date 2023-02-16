package Model

import Service.Candidato

class ModelCurtidasCandidatos {
    def connection

    ModelCurtidasCandidatos(connection) {
        this.connection = connection
    }

    void curtiVaga(Integer idVaga, Candidato candidato) {
        connection.sql.execute('''
                                    INSERT INTO curtidas_candidatos (id_vaga, id_candidato) 
                                        VALUES (?, (SELECT id FROM candidatos WHERE email = ?))
                               ''',
                [idVaga, candidato.email]
        )
    }

    boolean match(Integer idVaga, Candidato candidato) {
        boolean match = false

        connection.sql.query('''
                    SELECT * FROM curtidas_vagas WHERE id_vaga = ? AND id_candidato = (SELECT id FROM candidatos WHERE email = ?)
                    ''', [idVaga, candidato.email]) { resultSet ->

            if (resultSet.next()) {
                connection.sql.execute('''
                    INSERT INTO matchs (id_vaga, id_candidato) VALUES (?, (SELECT id FROM candidatos WHERE email = ?))
                    ''', [idVaga, candidato.email])

                match = true
            }
        }
        return match
    }
}
