package model.modelCompetencia

import service.competencia.Competencia
import groovy.sql.Sql

import java.sql.Connection

class ModelCompetencia {

    Sql sql

    ModelCompetencia(Connection connection) {
        this.sql = Sql.newInstance(connection)
    }

    void save(Competencia competencia) {
        sql.execute('''INSERT INTO competencias (competencia) VALUES (?)''', [competencia.name])
    }
}
