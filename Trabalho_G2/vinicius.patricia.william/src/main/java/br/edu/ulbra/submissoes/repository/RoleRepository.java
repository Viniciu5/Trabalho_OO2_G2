package br.edu.ulbra.submissoes.repository;

import br.edu.ulbra.submissoes.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);
}
