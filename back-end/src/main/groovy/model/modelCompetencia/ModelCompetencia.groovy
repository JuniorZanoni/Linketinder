package model.modelCompetencia

import service.competencia.Competencia
import groovy.sql.Sql

class ModelCompetencia {

    Sql connection

    ModelCompetencia(Sql connection) {
        this.connection = connection
    }

    void save(Competencia competencia) {
        connection.execute('''INSERT INTO competencias (competencia) VALUES (?)''', [competencia.name])
    }
}
