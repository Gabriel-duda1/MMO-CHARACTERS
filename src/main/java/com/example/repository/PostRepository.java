package com.example.repository;

import com.example.model.Post;
import com.example.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByPersonagemInOrderByDataCriacaoDesc(Set<Personagem> personagens);
    List<Post> findByPersonagemOrderByDataCriacaoDesc(Personagem personagem);
}