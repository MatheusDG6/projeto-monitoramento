/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.monitoramento.controller;

import java.util.List;
import com.projeto.monitoramento.model.FerramentaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.projeto.monitoramento.service.FerramentaService;

/**
 *
 * @author Aluno
 */
@Controller
public class FerramentaController {
    
    @Autowired
    private FerramentaService service;
    
    // Levar sempre para "ferramentas" caso chegar em index
    @GetMapping("/")
    public String index(){
        return "redirect:/ferramentas";
    }
    
    @GetMapping("/ferramentas")
    public String listarFerramentas(Model model){
        List<FerramentaBean> lista = service.listarFerramentas();
        model.addAttribute("ferramentas", lista);
        
        return "ferramenta";
    }
}
