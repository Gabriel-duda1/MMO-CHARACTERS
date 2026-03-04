package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.Personagem;
import com.example.model.Post;
import com.example.model.Usuario;
import com.example.repository.PersonagemRepository;
import com.example.repository.PostRepository;
import com.example.service.SocialService;
import com.example.service.UsuarioService;

@Controller
public class GamerController {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SocialService socialService;

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

    @GetMapping("/feed")
    public String mostrarFeed(Model model) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Usuario usuario = usuarioService.buscarPorUsername(username);
            
            List<Post> todosPosts = postRepository.findAll();
            
            if (todosPosts.isEmpty()) {
                List<Personagem> todosPersonagens = personagemRepository.findAll();
                for (Personagem p : todosPersonagens) {
                    socialService.criarPost(p, "Explorando as terras de " + p.getJogo().getNome() + "!");
                }
                todosPosts = postRepository.findAll();
            }
            
            model.addAttribute("usuario", usuario);
            model.addAttribute("posts", todosPosts);
            
            return "feed";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home-gamer?error=feed";
        }
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home-gamer";
    }
}