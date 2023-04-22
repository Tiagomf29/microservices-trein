package mspessoas.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mspessoas.domain.model.Person;
import mspessoas.domain.repository.PersonRepository;
import mspessoas.exceptions.ExceptionCpfExists;

@Service
public class PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person personCreate(Person person) throws ExceptionCpfExists {	
		logger.info("creating person...");
		
		if(personRepository.findByCpf(person.getCpf()).isPresent()) {
			throw new ExceptionCpfExists();
		}
		
		Person personCreate = null;
		try {
			personCreate = personRepository.save(person);
			logger.info("created person! Id:"+person.getId());
		}catch (Exception e) {
			logger.info("error creating person!"+e);
		}
		return personCreate;
	}
	
	public Person personUpdate(Person person, int id) {		
		person.setId(id);
		logger.info("updating person... Id:"+id);
		Person personCreate = null;
		try {
			personCreate = personRepository.save(person);
			logger.info("updated person!");
		}catch (Exception e) {
			logger.info("error updating person!"+e);
		}
		return personCreate;		
	}
	
	public void personDelete(int id) {
		logger.info("deleting person...");
		try {
			personRepository.deleteById(id);
			logger.info("deleted person! Id:"+id);
		}catch(Exception e){
			logger.info("error deleted person!");
		}		
	}
	
	public Page<Person> personListAll(Pageable pageable){
		logger.info("listing people...");
		Page<Person> personList = personRepository.findAll(pageable);
		logger.info("people listed...");
		return personList;
	}

}
