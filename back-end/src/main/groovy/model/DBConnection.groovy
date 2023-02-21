package model

import java.sql.Connection
import java.sql.DriverManager

class DBConnection {
    static Connection connection

    private DBConnection(){}

    static Connection getDBConnection() {
        if(connection == null) {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/postgres",
                    "postgres",
                    "postgres"
            )
        }

        return connection
    }
}
