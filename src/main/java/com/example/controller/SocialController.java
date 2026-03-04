package com.example.controller;

import com.example.model.*;
import com.example.service.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class SocialController {

    @Autowired private SocialService socialService;
    @Autowired private UsuarioService usuarioService;
    @Autowired private PostRepository postRepository;
    @Autowired private PersonagemRepository personagemRepository;

    private Personagem getPersonagemLogado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);
        List<Personagem> personagens = personagemRepository.findByUsuario(usuario);
        return personagens.isEmpty() ? null : personagens.get(0);
    }

    @PostMapping("/curtir/{postId}")
    public String curtir(@PathVariable Integer postId) {
        Personagem p = getPersonagemLogado();
        if (p != null && postId != null) {
            postRepository.findById(postId).ifPresent(post -> socialService.curtir(p, post));
        }
        return "redirect:/feed";
    }

    @PostMapping("/comentar/{postId}")
    public String comentar(@PathVariable Integer postId, @RequestParam String conteudo) {
        Personagem p = getPersonagemLogado();
        if (p != null && postId != null && !conteudo.isBlank()) {
            postRepository.findById(postId).ifPresent(post -> socialService.comentar(p, post, conteudo));
        }
        return "redirect:/feed";
    }

    @PostMapping("/seguir/{personagemId}")
    public String seguir(@PathVariable Integer personagemId) {
        Personagem seguidor = getPersonagemLogado();
        if (seguidor != null && personagemId != null) {
            personagemRepository.findById(personagemId).ifPresent(alvo -> {
                if (!seguidor.getId().equals(alvo.getId())) {
                    seguidor.getSeguindo().add(alvo);
                    personagemRepository.save(seguidor);
                }
            });
        }
        return "redirect:/feed";
    }
}