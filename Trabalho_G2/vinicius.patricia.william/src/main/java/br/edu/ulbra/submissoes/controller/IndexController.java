package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.model.*;
import br.edu.ulbra.submissoes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Paths;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	EventRepository eventoRepository;

	@RequestMapping("/")
	public String index(){
		return "redirect:/inicio";
	}

	@RequestMapping("/inicio")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		List<Event> eventos = (List<Event>) eventoRepository.findAll();
		mv.addObject("events", eventos);
		return mv;
	}
}
