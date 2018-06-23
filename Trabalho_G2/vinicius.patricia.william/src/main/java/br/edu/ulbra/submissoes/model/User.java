package br.edu.ulbra.submissoes.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String username;
    
    private String name;
    
    private String password;
    
	@Transient
	private String passwordConfirm;

	@ManyToMany
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;   
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Event> evento;
	
	@ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Article> artigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
	public Set<Event> getEvento() {
		return evento;
	}

	public void setEvento(Set<Event> evento) {
		this.evento = evento;
	}
	
	public Set<Article> getArtigo() {
		return artigo;
	}

	public void setArtigo(Set<Article> artigo) {
		this.artigo = artigo;
	}
}
