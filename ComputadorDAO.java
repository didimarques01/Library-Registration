/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import connection.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Computador;

/**
 *
 * @author Yuri Willians
 */
public class ComputadorDAO {
    public void create(Computador computador) throws SQLException {
    
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    
        try {
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO computador (id, status, disponibilidade)VALUE(?, ?, ?)");
            stmt.setString(1, computador.getId());
            stmt.setString(2, computador.getStatus());
            stmt.setString(3, computador.getDisponibilidade());
            if(computador.getId().isEmpty()){
                JOptionPane.showMessageDialog(null,"ID vazio");
            } else {
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
              
            } 
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
            }  finally {
                ConnectionFactory.closeConnection(con, stmt);
            }
    }
    
    public List<Computador> read() {
            
        try {
            
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<Computador> computadores = new ArrayList();
            
            try {
                stmt = con.prepareStatement("SELECT * FROM computador");
                rs = stmt.executeQuery();
                
                while (rs.next()) {
                    Computador computador = new Computador();
                    computador.setId(rs.getString("id"));
                    computador.setDisponibilidade(rs.getString("disponibilidade"));
                    computador.setStatus(rs.getString("status"));
                    computadores.add(computador);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            
            return computadores;
            
        } catch (SQLException ex) {
            Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        }
    
    public void update(Computador computador) throws SQLException {
    
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    
        try {
            stmt = (PreparedStatement) con.prepareStatement("UPDATE computador SET status = ?, disponibilidade = ? WHERE id = ?");
            stmt.setString(1, computador.getStatus());
            stmt.setString(2, computador.getDisponibilidade());
            stmt.setString(3, computador.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
     public void delete(Computador computador) throws SQLException {
    
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    
        try {
            stmt = (PreparedStatement) con.prepareStatement("DELETE FROM computador WHERE id = ?");
           
            stmt.setString(1, computador.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}