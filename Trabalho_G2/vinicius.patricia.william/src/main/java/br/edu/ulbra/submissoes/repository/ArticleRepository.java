package br.edu.ulbra.submissoes.repository;

import br.edu.ulbra.submissoes.model.Article;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
	List<Article> findByUser(User user);
	Article findByUserAndEvento(User username, Event evento);
}
