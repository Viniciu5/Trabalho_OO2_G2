package br.edu.ulbra.submissoes.model;

import javax.persistence.*;

import br.edu.ulbra.submissoes.model.User;

import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String artigo;

    @OneToMany(mappedBy = "event")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return artigo;
    }

    public void setDescription(String description) {
        this.artigo = description.trim();
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
