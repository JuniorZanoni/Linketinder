package Model

import Usuario.Candidato

class ModelCompetencia {

    void save(String competencia) {
        Connection.sql.execute('''INSERT INTO competencias (competencia) VALUES (?)''', [competencia])
    }

    List getCompetencias() {

        List competencias = []

        Connection.sql.query('''SELECT * FROM competencias;''') { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }

    List getCompetenciasUsuario(String email) {

        List competencias = []

        Connection.sql.query('''SELECT id_competencia, competencia FROM candidatos_competencias
                                        LEFT JOIN candidatos ON candidatos.id = candidatos_competencias.id_candidato
                                        LEFT JOIN competencias ON competencias.id = candidatos_competencias.id_competencia
                                        WHERE candidatos.email = ?;''', [email]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id_competencia').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }

    void addCompetenciaUsuario(Candidato candidato, Integer competencia) {
        Connection.sql.execute('''INSERT INTO candidatos_competencias (id_candidato, id_competencia) 
                                  VALUES (
                                        (SELECT id FROM candidatos WHERE email = ?), 
                                        (SELECT id FROM competencias WHERE id = ?))''',
                [candidato.email, competencia])
    }

    void deleteCompetenciaUsuario(Integer competencia, String email) {
        Connection.sql.execute('''DELETE FROM candidatos_competencias 
                                    WHERE id_competencia = ? AND id_candidato = (SELECT id FROM candidatos WHERE email = ?);''',
                [competencia, email])
    }

    List getCompetenciasVaga(Integer idVaga) {

        List competencias = []

        Connection.sql.query('''SELECT id_competencia, competencia FROM vagas_competencias
                                        LEFT JOIN competencias ON competencias.id = vagas_competencias.id_competencia
                                        WHERE id_vaga = ?;''', [idVaga]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id_competencia').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }

    void saveCompetenciaVaga(Integer idVaga, Integer idCompetencia) {
        Connection.sql.execute('''INSERT INTO vagas_competencias (id_vaga, id_competencia) VALUES (?, ?)''',
                [idVaga, idCompetencia])
    }

    void deleteCompetenciaVaga(Integer idVaga, Integer idCompetencia) {
        Connection.sql.execute('''DELETE FROM vagas_competencias 
                                    WHERE id_vaga = ? AND id_competencia = ?;''',
                [idVaga, idCompetencia])
    }

}
