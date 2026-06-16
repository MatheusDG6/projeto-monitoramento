/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.FerramentaBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class FerramentaDAO {
    
    public int criar(FerramentaBean ferramenta){
        
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("INSERT INTO tb_ferramenta (id, nome, horasUso, vidaUtilMaxima) VALUES (?,?,?,?)");
            stmt.setInt(1, ferramenta.getId());
            stmt.setString(2, ferramenta.getNome());
            stmt.setInt(3, ferramenta.getHorasUso());
            stmt.setInt(4, ferramenta.getVidaUtilMaxima());
            
            return stmt.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<FerramentaBean> listar(){
        List<FerramentaBean> ferramenta = new ArrayList();
        
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("SELECT * FROM tb_ferramenta");
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                FerramentaBean f = new FerramentaBean();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setHorasUso(rs.getInt("horasUso"));
                f.setVidaUtilMaxima(rs.getInt("vidaUtilMaxima"));
                
                ferramenta.add(f);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return ferramenta;
    }
    
    public void atualizar(FerramentaBean ferramentas){
        
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("UPDATE tb_ferramenta SET nome = ?, horasUso = ?, vidaUtilMaxima = ? WHERE id = ?");
            
            stmt.setString(1, ferramentas.getNome());
            stmt.setInt(2, ferramentas.getHorasUso());
            stmt.setInt(3, ferramentas.getVidaUtilMaxima());
            stmt.setInt(4, ferramentas.getId());
            
            stmt.executeUpdate();
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
        
    public void deletar(int id){
        
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("DELETE FROM tb_ferramenta WHERE id = ?");
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            stmt.close();
            stmt.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}