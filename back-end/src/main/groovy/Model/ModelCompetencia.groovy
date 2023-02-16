package Model

import Service.Candidato

class ModelCompetencia {
    def connection

    ModelCompetencia(connection) {
        this.connection = connection
    }

    void saveCompetencia(String competencia) {
        connection.sql.execute('''INSERT INTO competencias (competencia) VALUES (?)''', [competencia])
    }

    void saveCompeteciaUser(Candidato candidato, Integer idCompetencia) {
        connection.sql.execute('''INSERT INTO candidatos_competencias (id_candidato, id_competencia) 
                                  VALUES (
                                        (SELECT id FROM candidatos WHERE email = ?), 
                                        (SELECT id FROM competencias WHERE id = ?))''',
                [candidato.email, idCompetencia])
    }

    void removeCompetenciaUser(Candidato candidato, Integer idCompetencia) {
        connection.sql.execute('''DELETE FROM candidatos_competencias 
                                    WHERE id_competencia = ? AND id_candidato = (SELECT id FROM candidatos WHERE email = ?);''',
                [idCompetencia, candidato.email])
    }

    List loadAllCompetencias() {

        List competencias = []

        connection.sql.query('''SELECT * FROM competencias;''') { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }

    List loadCompetenciasUser(Candidato candidato) {

        List competencias = []

        connection.sql.query('''SELECT id, competencia FROM competencias
                                LEFT JOIN (SELECT * FROM candidatos_competencias WHERE id_candidato = 
                                (SELECT id FROM candidatos WHERE email = ?)) as candidatos_competencias
                                ON competencias.id = candidatos_competencias.id_competencia
                                WHERE id_candidato IS NOT NULL;''', [candidato.email]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }

    List loadCompetenciasNotUser(Candidato candidato) {

        List competencias = []

        connection.sql.query('''SELECT id, competencia FROM competencias
                                LEFT JOIN (SELECT * FROM candidatos_competencias WHERE id_candidato = 
                                (SELECT id FROM candidatos WHERE email = ?)) as candidatos_competencias
                                ON competencias.id = candidatos_competencias.id_competencia
                                WHERE id_candidato IS NULL;''', [candidato.email]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }
}
