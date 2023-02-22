package model.competencia

import groovy.sql.Sql
import model.DBConnection
import service.competencia.Competencia

import java.sql.Connection

class DAOCompetenciaVaga implements IDAOCompetencia{

    Sql sql = Sql.newInstance(DBConnection.getDBConnection())

    List<Competencia> getCompetencias(Integer idVaga) {
        List competencias = []

       sql.query('''SELECT id_competencia, competencia FROM vagas_competencias
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

       sql.query('''SELECT id, competencia FROM competencias
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

    void add(Integer idVaga, Integer idCompetencia) {
       sql.execute('''INSERT INTO vagas_competencias (id_vaga, id_competencia) VALUES (?, ?)''',
                [idVaga, idCompetencia])
    }

    void remove(Integer idVaga, Integer idCompetencia) {
       sql.execute('''DELETE FROM vagas_competencias 
                                    WHERE id_vaga = ? AND id_competencia = ?;''',
                [idVaga, idCompetencia])
    }
}
