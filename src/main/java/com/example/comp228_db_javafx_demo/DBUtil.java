package com.example.comp228_db_javafx_demo;
import java.sql.*;

public class DBUtil {
    private static Connection connection = null;
    private static Statement statement = null;

    public static void dbConnect() throws SQLException{
        String dbURL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
        String username = "COMP214_F23_shah_16";
        String password = "password";
        connection = DriverManager.getConnection(dbURL, username, password);
        System.out.println("Db Successfully Connected!");
        statement = connection.createStatement();
    }
    public static void dbDisconnect() throws SQLException{
        if(connection != null && !connection.isClosed()){
            connection.close();
            System.out.println("Db Successfully Disconnected!");
        }
    }
    public static void createTable(String tableName) throws SQLException{
        dbConnect();
        String sql = "CREATE TABLE " + tableName + "(s_id INTEGER PRIMARY KEY, s_name VARCHAR(100))";
        statement.execute(sql);
        System.out.println("Table was created");
        if (statement != null)statement.close();
        dbDisconnect();
    }
    public static void dropTable(String tableName) throws SQLException{
        dbConnect();
        String sql = "DROP TABLE " + tableName;
        System.out.println("Table dropped Succesfully");
        statement.execute(sql);
    }
    public static void insertData(String tableName, int id, String name) throws SQLException {
        dbConnect();
        String sql = "INSERT INTO " + tableName + " VALUES(" + id + ", '" + name + "')";
        statement.execute(sql);
        System.out.println(id + " and " + name + " were inserted");

        if (statement != null) statement.close();
        dbDisconnect();
    }
    public static ResultSet query(String sql) throws SQLException{
        dbConnect();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            String s_name = resultSet.getString("s_name");
            int s_id = resultSet.getInt("s_id");
            System.out.println(s_id + " | " + s_name);
        }
        if (statement != null) statement.close();
        dbDisconnect();
        return resultSet;
    }
    public static void deleteData(String tableName, int id) throws SQLException{
        dbConnect();

        String sql = "DELETE FROM " + tableName + " WHERE s_id = " + id;
        statement.execute(sql);
        System.out.println("Data was succesfully deleted");
        if (statement != null) statement.close();
        dbDisconnect();
    }

    public static void main (String [] args) throws SQLException {
        // database connection
        DBUtil.dbConnect();
        // disconnects the database
        DBUtil.dropTable("COMP228_M10");
        DBUtil.dbDisconnect();
        DBUtil.createTable("COMP228_M10");
        DBUtil.insertData("COMP228_M10", 100, "Sebastian");
        DBUtil.insertData("COMP228_M10", 200, "Benedict");
        DBUtil.insertData("COMP228_M10", 300, "David");
        query("SELECT * FROM COMP228_M10");

        DBUtil.deleteData("COMP228_M10", 200);
        query("SELECT * FROM COMP228_M10");
    }
}
