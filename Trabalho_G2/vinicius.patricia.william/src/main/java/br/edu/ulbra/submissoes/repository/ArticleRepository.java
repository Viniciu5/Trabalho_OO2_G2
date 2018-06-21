package br.edu.ulbra.submissoes.repository;

import br.edu.ulbra.submissoes.model.Article;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.model.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	List<Article> findByUser(User user);
	Article findByUserAndEvento(User username, Event evento);
}
