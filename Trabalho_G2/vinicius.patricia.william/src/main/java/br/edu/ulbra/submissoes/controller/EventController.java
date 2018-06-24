package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.config.RedirectConstants;
import br.edu.ulbra.submissoes.config.StringConstants;
import br.edu.ulbra.submissoes.input.ArtigoInput;
import br.edu.ulbra.submissoes.input.EventInput;
import br.edu.ulbra.submissoes.model.Article;
import br.edu.ulbra.submissoes.model.Role;
import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.repository.ArticleRepository;
import br.edu.ulbra.submissoes.repository.EventRepository;
import br.edu.ulbra.submissoes.service.interfaces.SecurityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventController {
	@Autowired
	SecurityService securityService;
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	EventRepository eventRepository;

	private ModelMapper mapper = new ModelMapper();

	@RequestMapping("/listarEventos")
	public ModelAndView minhaLista() {
		ModelAndView mv = new ModelAndView("eventos/listarEventos");
		mv.addObject(StringConstants.USER_LOGGED, securityService.findLoggedInUser());

		if (securityService.findLoggedInUser() != null && securityService.findLoggedInUser().getRoles() != null) {
			for(Role p : securityService.findLoggedInUser().getRoles()){
				if (p.getName().equals(StringConstants.ROLE_ADMIN)) {
					mv.addObject(StringConstants.ADMIN, true);
					break;
				}
				else {
					mv.addObject(StringConstants.ADMIN, false);
				}
			}
		}

		List<Article> articles = articleRepository.findByUser(securityService.findLoggedInUser());
		mv.addObject("articles", articles);
		return mv;
	}

	@RequestMapping("/evento/{id}")
	public ModelAndView detalhe(@PathVariable("id") Long idEvent, RedirectAttributes redirectAttrs) {
		Event event = eventRepository.findById(idEvent).get();

		if (event == null) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "O evento solicitado não existe.");
			return new ModelAndView(RedirectConstants.REDIRECT_INICIO);
		}

		ModelAndView mv = new ModelAndView("eventos/detalhe");
		mv.addObject(StringConstants.USER_LOGGED, securityService.findLoggedInUser());

		if (securityService.findLoggedInUser() != null && securityService.findLoggedInUser().getRoles() != null) {
			for(Role p : securityService.findLoggedInUser().getRoles()){
				if (p.getName().equals(StringConstants.ROLE_ADMIN)) {
					mv.addObject(StringConstants.ADMIN, true);
					break;
				}
				else {
					mv.addObject(StringConstants.ADMIN, false);
				}
			}
		}

		mv.addObject("articles", (event.getArtigos().isEmpty() ? null : event.getArtigos()));
		mv.addObject("event", event);
		return mv;
	}

	@GetMapping("/evento/{id}/artigo")
	public ModelAndView artigoForm(@PathVariable("id") Long idEvent, RedirectAttributes redirectAttrs){
		Event event = eventRepository.findById(idEvent).get();

		if (event == null) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, StringConstants.ERROR_EVENTO_NAO_EXISTE);
			return new ModelAndView(RedirectConstants.REDIRECT_INICIO);
		}

		ModelAndView mv = new ModelAndView("eventos/artigo");
		Article artigo = articleRepository.findByUserAndEvento(securityService.findLoggedInUser(), event);
		ArtigoInput artigoInput = mapper.map((artigo == null ? new Article() : artigo), ArtigoInput.class);
		mv.addObject("article", artigoInput);
		mv.addObject(StringConstants.USER_LOGGED, securityService.findLoggedInUser());

		if (securityService.findLoggedInUser() != null && securityService.findLoggedInUser().getRoles() != null) {
			for(Role p : securityService.findLoggedInUser().getRoles()){
				if (p.getName().equals(StringConstants.ROLE_ADMIN)) {
					mv.addObject(StringConstants.ADMIN, true);
					break;
				}
				else {
					mv.addObject(StringConstants.ADMIN, false);
				}
			}
		}

		mv.addObject("date", new Date());
		mv.addObject("event", event);
		return mv;
	}

	@PostMapping("/evento/{id}/artigo")
	public String enviarAvaliacao(@PathVariable("id") Long idEvent, ArtigoInput artigoInput, RedirectAttributes redirectAttrs){
		Event event = eventRepository.findById(idEvent).get();
		if (event == null) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, StringConstants.ERROR_EVENTO_NAO_EXISTE);
			return RedirectConstants.REDIRECT_INICIO;
		}

		Article artigo = articleRepository.findByUserAndEvento(securityService.findLoggedInUser(), event);
		if (artigo == null) {
			artigo = new Article();
			artigo.setData(new Date());
			artigo.setTitulo(artigoInput.getTitulo());
			artigo.setResumo(artigoInput.getResumo());
			artigo.setUser(securityService.findLoggedInUser());
			artigo.setEvento(event);
		} else {
			artigo.setData(new Date());
			artigo.setTitulo(artigoInput.getTitulo());
			artigo.setResumo(artigoInput.getResumo());
		}
		articleRepository.save(artigo);

		redirectAttrs.addFlashAttribute("success", "Artigo enviado com sucesso.");

		return "redirect:/eventos/evento/" + idEvent;
	}
	
	@GetMapping("/novo")
	public ModelAndView novoEventoForm(@ModelAttribute("event") EventInput event){

		ModelAndView mv = new ModelAndView("eventos/novo");
		mv.addObject(StringConstants.USER_LOGGED, securityService.findLoggedInUser());
		if (securityService.findLoggedInUser() != null && securityService.findLoggedInUser().getRoles() != null) {
			for(Role p : securityService.findLoggedInUser().getRoles()){
				if (p.getName().equals(StringConstants.ROLE_ADMIN)) {
					mv.addObject(StringConstants.ADMIN, true);
					break;
				}
				else {
					mv.addObject(StringConstants.ADMIN, false);
				}
			}
		}

		mv.addObject("event", event);
		return mv;
	}

	@PostMapping("/novo")
	public String novoEvento(EventInput eventInput, RedirectAttributes redirectAttrs) throws IOException {
		if (eventInput.getName().length() == 0)
		{
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Você precisa informar todos os campos.");
			redirectAttrs.addFlashAttribute("event", eventInput);
			return "redirect:/eventos/novo";
		}

		Event event = mapper.map(eventInput, Event.class);
		event.setUser(securityService.findLoggedInUser());
		eventRepository.save(event);

		redirectAttrs.addFlashAttribute(StringConstants.SUCCESS, "Evento cadastrado com sucesso.");
		return RedirectConstants.REDIRECT_INICIO;
	}

}
