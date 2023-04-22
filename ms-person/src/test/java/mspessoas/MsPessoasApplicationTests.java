package mspessoas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import mspessoas.domain.model.Person;
import mspessoas.domain.repository.PersonRepository;
import mspessoas.domain.service.PersonService;
import mspessoas.exceptions.ExceptionCpfExists;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MsPessoasApplicationTests {

	@Autowired
	PersonService personService;
	
	@Autowired
	PersonRepository personRepository;
	
	@Test
	@Order(value = 1)
	void initDeletedAllRecords() {
		personRepository.deleteAll();
		List<Person> listPerson = personRepository.findAll();
		assertTrue(listPerson.size()==0);
	}
	
	@Test
	@Order(value = 2)
	void createdPersonWithSucess() throws ExceptionCpfExists {
		
		Person p = new Person();
		p.setNome("Pessoa 1");
		p.setNascimento(LocalDate.parse("1986-05-29"));
		p.setNomeMae("Mae Pessoa 1");
		p.setNomePai("Nome pai Pessoa 1");
		p.setCpf("07346345491");
		Person personCreated =  personService.personCreate(p);
		assertTrue(personCreated.getId() > 0);
		
	}
	
	@Test
	@Order(value = 3)
	void updatePersonWhithSucess() {
		Optional<Person> person = personRepository.findByCpf("07346345491");
		if(person.isPresent()) {
			person.get().setNome("Pessoal 1 Atualizada");
			personService.personUpdate(person.get(), person.get().getId());
		}
		Optional<Person> personUpdated = personRepository.findByNome("Pessoal 1 Atualizada");
		assertTrue(personUpdated.isPresent());
	}
	
	@Test
	@Order(value = 5000)
	void EndDeletedAllRecords() {
		personRepository.deleteAll();
		List<Person> listPerson = personRepository.findAll();
		assertTrue(listPerson.size()==0);
	}

}
