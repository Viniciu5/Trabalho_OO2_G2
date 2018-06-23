package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.config.RedirectConstants;
import br.edu.ulbra.submissoes.config.StringConstants;
import br.edu.ulbra.submissoes.input.EventInput;
import br.edu.ulbra.submissoes.model.Article;
import br.edu.ulbra.submissoes.model.Role;
import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.repository.ArticleRepository;
import br.edu.ulbra.submissoes.repository.EventRepository;
import br.edu.ulbra.submissoes.service.interfaces.SecurityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/vinho")
public class AdminEventController {
	
	@Autowired
	EventRepository eventRepository;
	@Autowired
	SecurityService securityService;
	@Autowired
	ArticleRepository articleRepository;

	private ModelMapper mapper = new ModelMapper();

	@RequestMapping()
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("admin/evento/lista");
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

		mv.addObject(StringConstants.ADMIN, true);
		List<Event> events = (List<Event>) eventRepository.findAll();
		mv.addObject("events", events);
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novoEventoForm(@ModelAttribute("event") EventInput event){

		ModelAndView mv = new ModelAndView("admin/evento/novo");
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
			return "redirect:/admin/evento/novo";
		}

		Event event = mapper.map(eventInput, Event.class);
		eventRepository.save(event);

		redirectAttrs.addFlashAttribute(StringConstants.SUCCESS, "Evento cadastrado com sucesso.");
		return RedirectConstants.REDIRECT_ADMIN_EVENT;
	}

	@GetMapping("/{id}")
	public ModelAndView detalheEvento(@PathVariable("id") Long idEvent, RedirectAttributes redirectAttrs){
		Event event = eventRepository.findById(idEvent).get();

		if (event == null) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "O evento solicitado não existe.");
			return new ModelAndView("redirect:/admin/evento");
		}

		EventInput eventInput = mapper.map(event, EventInput.class);

		ModelAndView mv = new ModelAndView("admin/evento/detalhe");
		mv.addObject("articles", (event.getArtigos().isEmpty() ? null : event.getArtigos()));
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

		mv.addObject("event", eventInput);
		return mv;
	}

	@PostMapping("/{id}")
	public String salvarEvento(@PathVariable("id") Long idEvent, EventInput eventInput, RedirectAttributes redirectAttrs) throws IOException {
		Event event = eventRepository.findById(idEvent).get();

		if (event == null) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Esse evento não existe.");
			redirectAttrs.addFlashAttribute("user", eventInput);
			return RedirectConstants.REDIRECT_ADMIN_EVENT + idEvent;
		}

		event.setName(eventInput.getName());
		eventRepository.save(event);

		redirectAttrs.addFlashAttribute(StringConstants.SUCCESS, "Evento alterado com sucesso.");

		return RedirectConstants.REDIRECT_ADMIN_EVENT + idEvent;
	}

	@RequestMapping("/{id}/delete")
	public String deletarVinho(@PathVariable("id") Long idEvent, RedirectAttributes redirectAttrs) {
		Event event = eventRepository.findById(idEvent).get();
		if (event == null) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Não existe um evento com essa identificação.");
		} else {
			eventRepository.delete(event);
			redirectAttrs.addFlashAttribute(StringConstants.SUCCESS, "Evento deletado com sucesso.");
		}

		return "redirect:/admin/evento";
	}

	@RequestMapping("/{vid}/avaliacao/{id}/delete")
	public String deletarArtigo(@PathVariable("vid") Long idEvent, @PathVariable("id") Long idArtigo, RedirectAttributes redirectAttrs) {
		Article artigo = articleRepository.findById(idArtigo).get();
		if (artigo == null) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Não existe artigos com essa identificação.");
		} else {
			articleRepository.delete(artigo);
			redirectAttrs.addFlashAttribute(StringConstants.SUCCESS, "Artigo deletado com sucesso.");
		}

		return RedirectConstants.REDIRECT_ADMIN_EVENT + idEvent;
	}
}
