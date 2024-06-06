package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {

    public static boolean databaseExists(String dbName, String dbUrl, String user, String password) {
        String url = dbUrl + dbName;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            return true;
        } catch (SQLException e) {
            // Database does not exist
        }
        return false;
    }

    public static void createDatabase(String dbName, String dbUrl, String user, String password) {
        String url = dbUrl;
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            String sql = String.format("CREATE DATABASE " + dbName);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}