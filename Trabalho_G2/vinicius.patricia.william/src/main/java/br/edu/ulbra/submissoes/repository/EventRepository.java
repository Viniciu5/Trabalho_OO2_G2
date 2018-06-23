package br.edu.ulbra.submissoes.repository;

import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findByUser(User user);
}
