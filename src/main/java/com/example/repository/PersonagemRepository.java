package com.example.repository;

import com.example.model.Personagem;
import com.example.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {
    List<Personagem> findByUsuario(Usuario usuario);
    List<Personagem> findByNomeContainingIgnoreCase(String nome);
}