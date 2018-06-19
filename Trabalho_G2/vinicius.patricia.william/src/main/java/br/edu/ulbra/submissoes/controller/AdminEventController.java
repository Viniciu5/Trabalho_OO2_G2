package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.input.EventInput;
import br.edu.ulbra.submissoes.model.*;
import br.edu.ulbra.submissoes.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/evento")
public class AdminEventController {
	
	@Autowired
	EventRepository eventRepository;


	private ModelMapper mapper = new ModelMapper();

	@RequestMapping()
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("admin/evento/lista");
		List<Event> eventos = (List<Event>) eventRepository.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novoEventoForm(@ModelAttribute("artigo") EventInput evento){
		List<Event> eventos = (List<Event>)eventRepository.findAll();
		ModelAndView mv = new ModelAndView("admin/evento/novo");
		mv.addObject("eventos", eventos);
		return mv;
	}

    @GetMapping("/{eventId}")
    public  ModelAndView listarEvento(@PathVariable(name="eventId") Long eventId){
    	
        ModelAndView mv = new ModelAndView("evento/lista");
        Event evento = eventRepository.findById(eventId).get();
        EventInput artigoIn = new EventInput();
        mv.addObject("artigoInput",artigoIn);
        mv.addObject("artigo", evento.getArtigo());
        mv.addObject("evento", evento);
        
        return mv;
    }

	@PostMapping("/{eventId}")
	public void salvarEvento(@PathVariable("eventId") Long eventoId, EventInput artigoInput, RedirectAttributes redirectAttrs) throws IOException {
		Event evento = eventRepository.findById(eventoId).get();
		evento.setArtigo(artigoInput.getArtigo());

		eventRepository.save(evento);
	}

	@RequestMapping("/{eventId}/delete")
	public String deletarEvento(@PathVariable("eventId") Long eventoId, RedirectAttributes redirectAttrs) {
		Event evento = eventRepository.findById(eventoId).get();
		eventRepository.delete(evento);

		return "redirect:/admin/evento";
	}
}
