package model.modelCompetencia

import groovy.sql.Sql
import service.competencia.Competencia

class ModelCompetenciaCandidato implements IModelCompetencia {

    Sql connection

    ModelCompetenciaCandidato(Sql connection) {
        this.connection = connection
    }

    List<Competencia> getCompetencias(Integer idCandidato) {
        List competencias = []

        connection.query('''SELECT id, competencia FROM competencias
                                LEFT JOIN (SELECT * FROM candidatos_competencias WHERE id_candidato = 
                                (SELECT id FROM candidatos WHERE id = ?)) as candidatos_competencias
                                ON competencias.id = candidatos_competencias.id_competencia
                                WHERE id_candidato IS NOT NULL;''', [idCandidato]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }

    List getCompetenciasNoHave(Integer idCandidato) {

        List competencias = []

        connection.query('''SELECT id, competencia FROM competencias
                                LEFT JOIN (SELECT * FROM candidatos_competencias WHERE id_candidato = 
                                (SELECT id FROM candidatos WHERE id = ?)) as candidatos_competencias
                                ON competencias.id = candidatos_competencias.id_competencia
                                WHERE id_candidato IS NULL;''', [idCandidato]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }

    void saveCompetecia(Integer idCandidato, Integer idCompetencia) {
        connection.execute('''INSERT INTO candidatos_competencias (id_candidato, id_competencia) VALUES (?, ?);''',
                [idCandidato, idCompetencia])
    }

    void deleteCompetecia(Integer idCandidato, Integer idCompetencia) {
        connection.execute('''DELETE FROM candidatos_competencias WHERE id_competencia = ? AND id_candidato = ?;''',
                [idCompetencia.toInteger(), idCandidato])
    }
}