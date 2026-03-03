package com.example.controller;

import com.example.model.Usuario;
import com.example.model.Personagem;
import com.example.service.UsuarioService;
import com.example.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.ArrayList;

@Controller
public class GamerController {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/home-gamer")
    public String homeGamer(Model model) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Usuario usuario = usuarioService.buscarPorUsername(username);
            
            if (usuario == null) {
                return "redirect:/login?error=notfound";
            }

            List<Personagem> meusPersonagens = personagemRepository.findByUsuario(usuario);
            if (meusPersonagens == null) {
                meusPersonagens = new ArrayList<>();
            }
            
            model.addAttribute("usuario", usuario);
            model.addAttribute("personagens", meusPersonagens);
            
            return "home-gamer";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home-gamer";
    }
}