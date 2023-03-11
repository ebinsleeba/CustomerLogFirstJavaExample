package utility;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ebin
 */
public class DatabaseConnection {

    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/coviddatabase?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return connection;
    }
}
