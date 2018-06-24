package br.edu.ulbra.submissoes.service.interfaces;

import br.edu.ulbra.submissoes.model.User;

public interface UserService {

	void save(User user);

	User findByUsername(String username);
}
