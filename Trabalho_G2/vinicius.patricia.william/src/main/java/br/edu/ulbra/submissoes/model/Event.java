package br.edu.ulbra.submissoes.model;

import javax.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@OneToOne(optional = false)
	private User user;

    @Column(nullable=false)
    private String artigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public String getArtigo() {
        return artigo;
    }

    public void setArtigo(String artigo) {
        this.artigo = artigo.trim();
    }

}
