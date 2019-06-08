/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yuri Willians
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/dbregistro?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "dbregistro";

    public static Connection getConnection() throws SQLException {
        
        try {
            Class.forName(DRIVER);
            return (Connection) DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.toString());
           throw new RuntimeException("Erro na conexão", ex);
        }
        
        
    }
    public static void closeConnection(Connection con){ 
        try {
            if(con != null){
                con.close();
            }    
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){ 
       closeConnection(con);
       
        try {
            if(stmt != null){
                stmt.close();
            }    
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){ 
       closeConnection(con, stmt);
       
        try {
            if(rs != null){
               rs.close();
            }    
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

