package model.competencia

import groovy.sql.Sql
import model.DBConnection
import service.competencia.Competencia

class DAOCompetenciaCandidato implements IDAOCompetencia {

    Sql sql = Sql.newInstance(DBConnection.getDBConnection())

    List<Map<String, String>> getCompetencias(Integer idCandidato) {
        List<Map<String, String>> competencias = []

       sql.query('''SELECT id, competencia FROM competencias
                                LEFT JOIN (SELECT * FROM candidatos_competencias WHERE id_candidato = 
                                (SELECT id FROM candidatos WHERE id = ?)) as candidatos_competencias
                                ON competencias.id = candidatos_competencias.id_competencia
                                WHERE id_candidato IS NOT NULL;''', [idCandidato]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add("id": id, "name": competencia)
            }
        }

        return competencias
    }

    List<Map<String, String>> getCompetenciasNoHave(Integer idCandidato) {

        List<Map<String, String>> competencias = []

       sql.query('''SELECT id, competencia FROM competencias
                                LEFT JOIN (SELECT * FROM candidatos_competencias WHERE id_candidato = 
                                (SELECT id FROM candidatos WHERE id = ?)) as candidatos_competencias
                                ON competencias.id = candidatos_competencias.id_competencia
                                WHERE id_candidato IS NULL;''', [idCandidato]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add("id": id, "name": competencia)
            }
        }

        return competencias
    }

    void add(Integer idCandidato, Integer idCompetencia) {
       sql.execute('''INSERT INTO candidatos_competencias (id_candidato, id_competencia) VALUES (?, ?);''',
                [idCandidato, idCompetencia])
    }

    void remove(Integer idCandidato, Integer idCompetencia) {
       sql.execute('''DELETE FROM candidatos_competencias WHERE id_competencia = ? AND id_candidato = ?;''',
                [idCompetencia.toInteger(), idCandidato])
    }
}
