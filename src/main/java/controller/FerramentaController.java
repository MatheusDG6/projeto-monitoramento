/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.FerramentaService;

/**
 *
 * @author Aluno
 */
@Controller
public class FerramentaController {
    
    @Autowired
    private FerramentaService service;
            
    @GetMapping("/ferramentas")
    public String listarFerramenta(Model model){
        List<Model> listaFerramentas = service.listar();
        return "ferramentas";
    }
    
    
}
