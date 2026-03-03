package com.example.repository;

import com.example.model.Curtida;
import com.example.model.Post;
import com.example.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Integer> {
    Optional<Curtida> findByPersonagemAndPost(Personagem personagem, Post post);
    Long countByPost(Post post);
}