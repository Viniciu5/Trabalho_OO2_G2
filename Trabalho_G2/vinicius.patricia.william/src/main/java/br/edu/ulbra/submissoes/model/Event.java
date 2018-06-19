package br.edu.ulbra.submissoes.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String artigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtigo() {
        return artigo;
    }

    public void setArtigo(String artigo) {
        this.artigo = artigo.trim();
    }

}
