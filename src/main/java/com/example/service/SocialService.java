package com.example.service;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class SocialService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public void criarPost(Personagem p, String conteudo) {
        Post post = new Post();
        post.setConteudo(conteudo);
        post.setPersonagem(p);
        postRepository.save(post);
    }

    public void comentar(Personagem p, Post post, String conteudo) {
        Comentario c = new Comentario();
        c.setConteudo(conteudo);
        c.setPersonagem(p);
        c.setPost(post);
        comentarioRepository.save(c);
    }

    public List<Post> getFeed(Set<Personagem> seguindo) {
        return postRepository.findByPersonagemInOrderByDataCriacaoDesc(seguindo);
    }
}