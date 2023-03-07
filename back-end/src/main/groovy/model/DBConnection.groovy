package model

import java.sql.Connection
import java.sql.DriverManager

class DBConnection {
    static Connection connection

    private DBConnection(){}

    static Connection getDBConnection() {
        if(connection == null) {
            String usuario = "postgres";
            String senha = "postgres";
            String nomeBancoDados = "postgres";

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + nomeBancoDados,
                        usuario, senha);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }

        return connection
    }
}
