package model.modelCompetencia

import groovy.sql.Sql
import service.competencia.Competencia

class ModelCompetenciaVaga implements IModelCompetencia{

    Sql connection

    ModelCompetenciaVaga(Sql connection) {
        this.connection = connection
    }

    List<Competencia> getCompetencias(Integer idVaga) {
        List competencias = []

        connection.query('''SELECT id_competencia, competencia FROM vagas_competencias
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

    List getCompetenciasNoHave(Integer idVaga) {
        List competencias = []

        connection.query('''SELECT id, competencia FROM competencias
                                LEFT JOIN (SELECT * FROM vagas_competencias WHERE id_vaga = ?) as vagas_competencias
                                ON competencias.id = vagas_competencias.id_competencia
                                WHERE id_vaga IS NULL;''', [idVaga]) { resultSet ->
            while (resultSet.next()) {
                String id = resultSet.getInt('id').toString()
                String competencia = resultSet.getString('competencia').toString()
                competencias.add([id, competencia])
            }
        }

        return competencias
    }

    void saveCompetecia(Integer idVaga, Integer idCompetencia) {
        connection.execute('''INSERT INTO vagas_competencias (id_vaga, id_competencia) VALUES (?, ?)''',
                [idVaga, idCompetencia])
    }

    void deleteCompetecia(Integer idVaga, Integer idCompetencia) {
        connection.execute('''DELETE FROM vagas_competencias 
                                    WHERE id_vaga = ? AND id_competencia = ?;''',
                [idVaga, idCompetencia])
    }
}
