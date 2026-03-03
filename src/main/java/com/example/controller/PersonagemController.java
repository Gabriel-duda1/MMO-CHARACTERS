package com.example.controller;

import com.example.model.Personagem;
import com.example.model.Usuario;
import com.example.service.UsuarioService;
import com.example.repository.PersonagemRepository;
import com.example.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonagemController {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/personagem/criar")
    public String formPersonagem(Model model) {
        model.addAttribute("jogos", jogoRepository.findAll());
        return "criar-personagem";
    }

    @PostMapping("/personagem/criar")
    public String criar(Personagem personagem) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);
        
        if (usuario != null) {
            personagem.setUsuario(usuario);
            personagemRepository.save(personagem);
        }
        
        return "redirect:/home-gamer";
    }

    @GetMapping("/personagem/perfil/{id}")
    public String perfil(@PathVariable("id") Integer id, Model model) {
        if (id == null) {
            return "redirect:/home-gamer";
        }

        Personagem p = personagemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personagem inválido: " + id));
        
        model.addAttribute("personagem", p);
        return "perfil-personagem";
    }
}