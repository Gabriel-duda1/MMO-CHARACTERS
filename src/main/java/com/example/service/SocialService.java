package com.example.service;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class SocialService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @Transactional
    public void criarPost(Personagem p, String conteudo) {
        Post post = new Post();
        post.setConteudo(conteudo);
        post.setPersonagem(p);
        postRepository.save(post);
    }

    @Transactional
    public void comentar(Personagem p, Post post, String conteudo) {
        Comentario c = new Comentario();
        c.setConteudo(conteudo);
        c.setPersonagem(p);
        c.setPost(post);
        comentarioRepository.save(c);
    }

    @Transactional
    public void curtir(Personagem p, Post post) {
        if (curtidaRepository.findByPersonagemAndPost(p, post).isEmpty()) {
            Curtida curtida = new Curtida();
            curtida.setPersonagem(p);
            curtida.setPost(post);
            curtidaRepository.save(curtida);
        }
    }

    @Transactional
    public void seguir(Personagem seguidor, Personagem alvo) {
        if (!seguidor.getId().equals(alvo.getId())) {
            seguidor.getSeguindo().add(alvo);
            personagemRepository.save(seguidor);
        }
    }

    public List<Post> getFeedGlobal() {
        return postRepository.findAll();
    }

    public List<Post> getFeedSeguidores(Set<Personagem> seguindo) {
        return postRepository.findByPersonagemInOrderByDataCriacaoDesc(seguindo);
    }
}