package br.edu.ulbra.submissoes.input;

import org.springframework.web.multipart.MultipartFile;

public class ArtigoInput {
	
	private String titulo;
	private String resumo;
	private MultipartFile texto;
	private String nomeTexto;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	
	public MultipartFile getTexto() {
		return texto;
	}

	public void setTexto(MultipartFile texto) {
		this.texto = texto;
	}

	public String getNomeTexto() {
		return nomeTexto;
	}

	public void setNomeTexto(String nomeTexto) {
		this.nomeTexto = nomeTexto;
	}
}
