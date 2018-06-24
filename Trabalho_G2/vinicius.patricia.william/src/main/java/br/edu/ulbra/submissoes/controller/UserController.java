package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.config.RedirectConstants;
import br.edu.ulbra.submissoes.config.StringConstants;
import br.edu.ulbra.submissoes.input.UserInput;
import br.edu.ulbra.submissoes.model.Role;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.RoleRepository;
import br.edu.ulbra.submissoes.repository.UserRepository;
import br.edu.ulbra.submissoes.service.interfaces.SecurityService;
import br.edu.ulbra.submissoes.service.interfaces.UserService;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/usuario")
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	SecurityService securityService;

	private ModelMapper mapper = new ModelMapper();

	@GetMapping("/novo")
	public ModelAndView newUserForm(@ModelAttribute("user") UserInput user){
		ModelAndView mv = new ModelAndView("user/new");
		mv.addObject("user", user);

		if (securityService.findLoggedInUser() != null && securityService.findLoggedInUser().getRoles() != null) {
			for(Role p : securityService.findLoggedInUser().getRoles()){
				if (p.getName().equals("ROLE_ADMIN")) {
					mv.addObject("admin", true);
					break;
				}
				else {
					mv.addObject("admin", false);
				}
			}
		}

		return mv;
	}

	@PostMapping("/novo")
	public String newUser(UserInput userInput, RedirectAttributes redirectAttrs){
		User usuario = userRepository.findByUsername(userInput.getUsername());

		if (usuario != null) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Um usuário com esse email já está cadastrado.");
			redirectAttrs.addFlashAttribute("user", userInput);
			return "redirect:/usuario/novo";
		}

		if (userInput.getPassword().length() == 0) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Uma senha deve ser informada.");
			redirectAttrs.addFlashAttribute("user", userInput);
			return "redirect:/admin/usuario/novo";
		}

		if (!userInput.getPassword().equals(userInput.getPasswordConfirm())) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Senha e confirmação de senha não são iguais.");
			redirectAttrs.addFlashAttribute("user", userInput);
			return "redirect:/usuario/novo";
		}

		Role role = roleRepository.findByName("ROLE_USER");
		User user = mapper.map(userInput, User.class);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		userService.save(user);

		redirectAttrs.addFlashAttribute("success", "Usuário cadastrado com sucesso. Você já pode entrar no sistema.");
		return "redirect:/";
	}	
	
	//editar
	
	@GetMapping("/detalhe")
	public ModelAndView detalheUsuario(){
		User usuario = securityService.findLoggedInUser();

		UserInput userInput = mapper.map(usuario, UserInput.class);

		ModelAndView mv = new ModelAndView("user/detalhe");
		mv.addObject("user", userInput);
		return mv;
	}

	@PostMapping("/detalhe")
	public String salvarUsuario(UserInput userInput, RedirectAttributes redirectAttrs) {
		User usuario = securityService.findLoggedInUser();

		if (usuario == null) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Esse usuário não existe.");
			redirectAttrs.addFlashAttribute("user", userInput);
			return "redirect:/";
		}

		User usuarioTest = userRepository.findByUsername(userInput.getUsername());
		if (usuarioTest != null && !usuario.getUsername().equals(usuarioTest.getUsername())) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Um usuário com esse email já está cadastrado.");
			redirectAttrs.addFlashAttribute("user", userInput);
			return RedirectConstants.REDIRECT_INICIO;
		}

		if (userInput.getPassword().length() != 0 && !userInput.getPassword().equals(userInput.getPasswordConfirm())) {
			redirectAttrs.addFlashAttribute(StringConstants.ERROR, "Senha e confirmação de senha não são iguais.");
			redirectAttrs.addFlashAttribute("user", userInput);
			return RedirectConstants.REDIRECT_INICIO;
		}

		usuario.setName(userInput.getName());
		usuario.setUsername(userInput.getUsername());
		if (userInput.getPassword().length() != 0) {
			usuario.setPassword(userInput.getPassword());
		}

		userService.save(usuario);

		redirectAttrs.addFlashAttribute(StringConstants.SUCCESS, "Usuário alterado com sucesso.");
		redirectAttrs.addFlashAttribute("user", userInput);
		return RedirectConstants.REDIRECT_INICIO;
	}
	
}
