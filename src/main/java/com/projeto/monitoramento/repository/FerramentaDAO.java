/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.monitoramento.repository;

import com.projeto.monitoramento.repository.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.projeto.monitoramento.model.FerramentaBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class FerramentaDAO {
    
    public int criar(FerramentaBean ferramenta){
        int linhas = 0;
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("INSERT INTO tb_ferramenta (nome, horasUso, vidaUtilMaxima) VALUES (?,?,?)");
            stmt.setString(1, ferramenta.getNome());
            stmt.setInt(2, ferramenta.getHorasUso());
            stmt.setInt(3, ferramenta.getVidaUtilMaxima());
            
            return stmt.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return linhas;
    }
    
    public List<FerramentaBean> listar(){
        List<FerramentaBean> lista = new ArrayList();
        
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
                
                lista.add(f);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public int atualizar(FerramentaBean ferramentas){
        int linhas = 0;
        
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("UPDATE tb_ferramenta SET nome = ?, horasUso = ?, vidaUtilMaxima = ? WHERE id = ?");
            
            stmt.setString(1, ferramentas.getNome());
            stmt.setInt(2, ferramentas.getHorasUso());
            stmt.setInt(3, ferramentas.getVidaUtilMaxima());
            stmt.setInt(4, ferramentas.getId());
            
            linhas = stmt.executeUpdate();
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return linhas;
    }
        
    public int deletar(int id){
        int linhas = 0;
        
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("DELETE FROM tb_ferramenta WHERE id = ?");
            
            stmt.setInt(1, id);
            
            linhas = stmt.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return linhas;
    }
}