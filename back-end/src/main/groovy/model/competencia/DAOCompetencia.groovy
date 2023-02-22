package model.competencia

import model.DBConnection
import service.competencia.Competencia
import groovy.sql.Sql

class DAOCompetencia {

    Sql sql = Sql.newInstance(DBConnection.getDBConnection())

    boolean save(Competencia competencia) {
        sql.execute('''INSERT INTO competencias (competencia) VALUES (?)''', [competencia.name])
    }
}
