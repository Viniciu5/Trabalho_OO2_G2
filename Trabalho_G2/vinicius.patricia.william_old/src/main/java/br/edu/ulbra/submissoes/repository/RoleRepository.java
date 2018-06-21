package br.edu.ulbra.submissoes.repository;

import br.edu.ulbra.submissoes.model.Role;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Set<Role> findAllByName(String name);
}
