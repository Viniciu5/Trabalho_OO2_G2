package br.edu.ulbra.submissoes.repository;

import br.edu.ulbra.submissoes.model.*;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
