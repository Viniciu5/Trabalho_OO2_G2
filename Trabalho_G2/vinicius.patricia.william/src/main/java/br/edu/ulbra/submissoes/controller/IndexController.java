package br.edu.ulbra.submissoes.controller;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.submissoes.model.*;
import br.edu.ulbra.submissoes.repository.EventRepository;

@Controller
public class IndexController {
	@Autowired
	EventRepository eventRepository;

	@RequestMapping("/")
	public String index(){
		return "redirect:/inicio";
	}

	@RequestMapping("/inicio")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		return mv;

		}

		List<Event> evento = (List<Event>) eventRepository.findAll();
	

	@RequestMapping("/minhalista")
	public ModelAndView minhalista() {
		ModelAndView mv = new ModelAndView("lista");
		return mv;
		}

	@GetMapping("/login")
	public ModelAndView loginForm(){
		return new ModelAndView("login/login");
	}

	@GetMapping("/denied")
	public ModelAndView denied(){
		return new ModelAndView("denied");
	}
}

