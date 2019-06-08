/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Registro;

/**
 *
 * @author Yuri Willians
 */
public class RegistroDAO {
     public void create(Registro registro) throws SQLException {
    
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    
        try {
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO registro (matricula, nome, horario, computador)VALUE(?, ?, ?, ?)");
            stmt.setString(1, registro.getMatricula());
            stmt.setString(2, registro.getNome());
            stmt.setString(3, registro.getHorario());
            stmt.setString(4, registro.getComputador());
            
            if (registro.getMatricula().isEmpty() || registro.getNome().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome e/ou Matricula vazio(s)");
            } else {
                stmt.executeUpdate();
            
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
   
    }
     
     public List<Registro> read() {
            
        try {
            
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<Registro> registros = new ArrayList();
            
            try {
                stmt = con.prepareStatement("SELECT * FROM registro");
                rs = stmt.executeQuery();
                
                while (rs.next()) {
                    Registro registro = new Registro();
                    registro.setNome(rs.getString("nome"));
                    registro.setComputador(rs.getString("computador"));
                    registro.setMatricula(rs.getString("matricula"));
                    registro.setHorario(rs.getString("horario"));
                    registros.add(registro);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            
            return registros;
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        }
     
      public void update(Registro registro) throws SQLException {
    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
    
        try {
            stmt = (PreparedStatement) con.prepareStatement("UPDATE registro SET horario = ?, nome = ? WHERE matricula = ?");
            stmt.setString(1, registro.getHorario());
//            stmt.setString(2, registro.getComputador());
            stmt.setString(2, registro.getNome());
            stmt.setString(3, registro.getMatricula());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
       public void delete(Registro registro) throws SQLException{
         Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM registro WHERE matricula = ?");
            stmt.setString(1, registro.getMatricula());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");    
             
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
      }
}