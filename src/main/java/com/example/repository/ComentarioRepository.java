package com.example.repository;

import com.example.model.Comentario;
import com.example.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByPostOrderByDataCriacaoAsc(Post post);
}