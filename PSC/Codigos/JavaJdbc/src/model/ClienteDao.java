        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;

import Entidades.Cliente;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author emerson
 */
public class ClienteDao{

    public void Create(Cliente cliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
    
        try {
            stmt = con.prepareStatement("insert into cliente (nome,sexo,dataNasc,estado,cidade,bairro,numero,rua,telefone)values(?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            stmt.setDate(3, cliente.getDataNasc());
            stmt.setString(4, cliente.getEstado());
            stmt.setString(5, cliente.getCidade());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getNumero());
            stmt.setString(8, cliente.getRua());
            stmt.setString(9, cliente.getTelefone());
            
            
            stmt.executeUpdate();
        
        } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Cliente");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public List <Cliente> Read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt =null;
        ResultSet rs = null;
        
        List <Cliente> clientes =new ArrayList();
        
        try {
            stmt = con.prepareStatement("Select * from cliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setDataNasc(rs.getDate("dataNasc"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setRua(rs.getString("rua"));
                cliente.setTelefone(rs.getString("telefone"));
                
                clientes.add(cliente);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
        
    }
    
    public List<Cliente> ReadForName(String nome){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt =null;
        ResultSet rs = null;
        
        List <Cliente> clientes =new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("Select * from cliente WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setDataNasc(rs.getDate("dataNasc"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setRua(rs.getString("rua"));
                cliente.setTelefone(rs.getString("telefone"));
                
                clientes.add(cliente);
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Nenhum Cliente Encontrado");
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
        
    }
    
     public void  Update(Cliente cliente){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt =null;
         
        try {
            stmt = con.prepareStatement("Update cliente Set nome=?,sexo=?,dataNasc=?,estado=?,cidade=?,bairro=?,numero=?,rua=?,telefone=? Where id = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            stmt.setDate(3, cliente.getDataNasc());
            stmt.setString(4, cliente.getEstado());
            stmt.setString(5, cliente.getCidade());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getNumero());
            stmt.setString(8, cliente.getRua());
            stmt.setString(9, cliente.getTelefone());
            stmt.setInt(10, cliente.getId());
             
             stmt.executeUpdate();
             JOptionPane.showMessageDialog(null,"Dados Atualizado com Sucesso!");
             
            
        } catch (Exception e) {
               JOptionPane.showMessageDialog(null,"Erro ao Atualizar Dados!"+e);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
   
    public void  Delete(Cliente cliente){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt =null;
         
        try {
             stmt = con.prepareStatement("Delete from cliente where id = ?");
             stmt.setInt(1, cliente.getId());
             
             stmt.executeUpdate();
             JOptionPane.showMessageDialog(null,"Cliente Excluido com Sucesso!");
             
            
        } catch (Exception e) {
               JOptionPane.showMessageDialog(null,"Erro ao Excluir Cliente!"+e);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
}