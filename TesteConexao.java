/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import connection.ConnectionFactory;
import java.sql.SQLException;

/**
 *
 * @author Yuri Willians
 */
public class TesteConexao  {
    public static void main(String[] args) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        if (con != null) {
            System.out.println("Conectado");
            
        } else {
            System.out.println("Não conectado");
        }
    }

    
}
