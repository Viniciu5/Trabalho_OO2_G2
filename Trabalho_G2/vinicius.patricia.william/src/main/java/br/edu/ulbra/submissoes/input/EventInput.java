package br.edu.ulbra.submissoes.input;

public class EventInput {

    private Long id;
    private String artigo;

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
        this.artigo = description;
    }

}