package br.edu.ulbra.listacompra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ulbra.listacompra.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
