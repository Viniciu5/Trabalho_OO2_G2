package br.edu.ulbra.submissoes.controller;

import org.springframework.stereotype.Controller;

import br.edu.ulbra.submissoes.input.*;
import br.edu.ulbra.submissoes.model.*;
import br.edu.ulbra.submissoes.repository.*;
import br.edu.ulbra.submissoes.repository.EventRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;



@Controller
@RequestMapping("/evento")
public class EventController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;

    private ModelMapper mapper = new ModelMapper();

    private ModelAndView artigoForm(EventInput comentarioIn){
	        ModelAndView mv = new ModelAndView("evento/novo");
	        return mv;
	    }
	
	
    @GetMapping("/listarEvento")
    public ModelAndView listarEventos(){
        ModelAndView mv = new ModelAndView("evento/listarEvento");
        List<Event> eventos = (List<Event>) eventRepository.findAll();
        mv.addObject("evento", eventos);
        return mv;


    }

    @GetMapping("/{eventId}")
    public  ModelAndView listarEvento(@PathVariable(name="eventId") Long eventId){
    	
        ModelAndView mv = new ModelAndView("evento/verEvento");
        Event evento = eventRepository.findById(eventId).get();
        EventInput artigoIn = new EventInput();
        mv.addObject("artigoInput",artigoIn);
        mv.addObject("artigo", evento.getArtigo());
        mv.addObject("evento", evento);
        
        return mv;
    }

    @PostMapping("/{eventId}")
    public ModelAndView addArtigo(EventInput artigoIn,@PathVariable(name="eventId") Long eventId){
       
		ModelAndView mv = this.artigoForm(artigoIn);
		Event evento = eventRepository.findById(eventId).get();
		mv.setViewName("evento/verEvento");
		mv.addObject("artigoInput",artigoIn);
		mv.addObject("artigo", evento.getArtigo());
		mv.addObject("evento", evento);
                       
		return mv;
    }
}
