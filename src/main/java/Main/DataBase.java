package Main;

import java.sql.Connection;
import java.sql.DriverManager;

import Credentials.Credentials;
import org.postgresql.Driver;


public class DataBase{
    public static void main(String[] args) {
            Connection c = null;
            String url = Credentials.DB_URL;
            String username = Credentials.DB_USERNAME;
            String password = Credentials.DB_PASSWORD;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);
            }
            System.out.println("Opened database successfully");
    }
}
