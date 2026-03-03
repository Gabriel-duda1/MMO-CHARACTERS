package com.example.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 50)
    private String classe;

    private Integer nivel = 1;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "jogo_id", nullable = false)
    private Jogo jogo;

    @ManyToMany
    @JoinTable(
        name = "personagem_seguidor",
        joinColumns = @JoinColumn(name = "personagem_id"),
        inverseJoinColumns = @JoinColumn(name = "seguidor_id")
    )
    private Set<Personagem> seguindo = new HashSet<>();

    public Personagem() {}

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }

    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Jogo getJogo() { return jogo; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public Set<Personagem> getSeguindo() { return seguindo; }
    public void setSeguindo(Set<Personagem> seguindo) { this.seguindo = seguindo; }
}