package mspessoas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mspessoas.domain.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

	Optional<Person>findByCpf(String cpf);
	Optional<Person>findByNome(String name);
	
}
