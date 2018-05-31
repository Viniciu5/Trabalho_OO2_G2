package br.edu.ulbra.listacompra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	

	@GetMapping("/")
	public String indexado(){
		
		return"index";
	}
	
	@GetMapping("/evento")
	public String eventos() {
		
		return "evento";
	}
	
	@GetMapping("/evento/{id}")
	public String eventoID() {
		
		return "eventoid";
	}
	
	@PostMapping("/evento")
	public String eventoPost() {
		
		return "eventoP";
	}
	
	@PostMapping("/evento/{id}")
	public String eventoPId() {
		
		return "eventoPid";
	}
	
	@GetMapping("/evento/{id}/delete")
	public String eventoIdDelete() {
		
		return "eventoidDelete";
	}
	
	@GetMapping("/usuario/{id}")
	public String usuarioID() {
		
		return "usuarioid";
	}

	
	@PostMapping("/usuario/{id}")
	public String usuarioPID() {
		
		return "usuarioPid";
	}

	@GetMapping("/usuario/cadastro")
	public String usuarioCad() {
		
		return "usuariocad";
	}

	
	@PostMapping("/usuario/cadastro")
	public String usuarioPCad() {
		
		return "usuarioPcad";
	}
	
	@GetMapping("/submissoes")
	public String submissao() {
		
		return "submissoes";
	}
	
	@GetMapping("/submissoes/{id}")
	public String submissaoId() {
		
		return "submissoesId";
	}
	
	@PostMapping("/submissoes/{id}")
	public String submissaoPId() {
		
		return "submissoesPId";
	}
	
	@PostMapping("/submissoes/{id}/delete")
	public String submissaoPIdDelete() {
		
		return "submissoesPIdDelete";
	}


	@GetMapping("/submissoes/evento/{idEvento}")
	public String submissaoEvento() {
		
		return "submissoesevento";
	}
	
	@PostMapping("/submissoes/evento/{idEvento}")
	public String submissaoPEvento() {
		
		return "submissoesPevento";
	}
	
	
}
