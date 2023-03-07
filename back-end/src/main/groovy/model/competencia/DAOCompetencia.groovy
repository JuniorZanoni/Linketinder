package model.competencia

import groovy.sql.Sql
import model.DBConnection
import service.competencia.Competencia

class DAOCompetencia {

    Sql sql = Sql.newInstance(DBConnection.getDBConnection())

    boolean save(Competencia competencia) {
        sql.execute('''INSERT INTO competencias (competencia) VALUES (?)''', [competencia.name])
    }
}
