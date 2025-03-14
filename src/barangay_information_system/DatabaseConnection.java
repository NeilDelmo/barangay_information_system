/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barangay_information_system;

/**
 *
 * @author Neil
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/barangay_information_system";
    private final static String USER = "root";
    private final static String PASS  = "";
    
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Database Connected Successfully");
        }catch(SQLException e){
            e.printStackTrace();
            
        }
        return connection;
    }
    
}
