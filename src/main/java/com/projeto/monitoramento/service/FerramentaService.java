/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.monitoramento.service;

import java.util.List;
import com.projeto.monitoramento.model.FerramentaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.projeto.monitoramento.repository.FerramentaDAO;

/**
 *
 * @author Aluno
 */
@Service
public class FerramentaService {
    
    @Autowired
    private FerramentaDAO repository;
    
    public List<FerramentaBean> listarFerramentas(){
        return repository.listar();
    }
    
    public void criar(FerramentaBean f){
        if(f.getNome().equals("")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Nome não preenchido!");
        }
        if(f.getVidaUtilMaxima() <= 0){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Vida util invalida!");
        }
        if(f.getHorasUso() < 0){
            f.setHorasUso(0);
            throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Horas de uso invalidas!");
        }
        int linhas = repository.criar(f);
        if(linhas == 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Erro ao adicionar ao Banco de dados");
        }
    }
}
