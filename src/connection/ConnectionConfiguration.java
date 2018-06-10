package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC", "root", "newrootpassword");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void chceckConnection(){
        Connection connection = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            if (connection != null){
                System.out.printf("Connection established");
            }
        } catch (Exception e){
            System.out.printf("Couldn't estalish connection");
            e.printStackTrace();
        }
    }

}
