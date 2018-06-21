package br.edu.ulbra.submissoes.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import br.edu.ulbra.submissoes.model.Article;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@ManyToOne(optional = false)
	private User user;
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Article> artigos;

    @Column(nullable=false)
    private String nome;
    
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date abertura;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechamento;

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

    public String getNome() {
        return nome;
    }

	public Set<Article> getArtigos() {
		return artigos;
	}

	public void setArtigos(Set<Article> artigos) {
		this.artigos = artigos;
	}
	
	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}
	
	public Date getFechamento() {
		return fechamento;
	}

	public void setFechamento(Date fechamento) {
		this.fechamento = fechamento;
	}

}
