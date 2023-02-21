package model

import groovy.sql.Sql

class DBConnection {
    static Sql connection = Sql.newInstance(
            "jdbc:postgresql://localhost/postgres",
            "postgres",
            "postgres",
            "org.postgresql.Driver"
    )

    private DBConnection(){}

    static Sql getDBConnection() {
        return connection
    }
}
