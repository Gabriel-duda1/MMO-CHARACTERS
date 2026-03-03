package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "curtida")
public class Curtida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "personagem_id", nullable = false)
    private Personagem personagem;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Curtida() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Personagem getPersonagem() { return personagem; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
}