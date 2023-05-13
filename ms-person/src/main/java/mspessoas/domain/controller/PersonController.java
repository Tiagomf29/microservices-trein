package mspessoas.domain.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mspessoas.domain.model.Person;
import mspessoas.domain.service.PersonService;
import mspessoas.dto.domain.model.request.PersonRequestDto;
import mspessoas.dto.domain.model.response.PersonResponseDto;
import mspessoas.exceptions.ExceptionCpfExists;

@RestController
@RequestMapping("/v1/persons")
@CrossOrigin(origins = "http://localhost:8081")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PersonResponseDto PersonCreate(@RequestBody @Valid PersonRequestDto person) throws ExceptionCpfExists {		
		Person personNoDto = modelMapper.map(person, Person.class);		
		return modelMapper.map(personService.personCreate(personNoDto), PersonResponseDto.class);
	}
	
	@PutMapping("/{id}")
	public PersonResponseDto PersonUpdate(@RequestBody Person person,@PathVariable int id) {
		person.setId(id);
		return modelMapper.map(personService.personUpdate(person, id), PersonResponseDto.class);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void PersonDelete(@PathVariable int id) {
		personService.personDelete(id);
	}
	
	@GetMapping
	public Page<PersonResponseDto>PersonListAll(Pageable pageable){
		
		Page<Person> pagePerson =  personService.personListAll(pageable);
		List<PersonResponseDto> personDto = new ArrayList<>();
		for(Person person : pagePerson.getContent()) {
			personDto.add(modelMapper.map(person, PersonResponseDto.class));
		}
		
		return new PageImpl<>(personDto, pageable, personDto.size());
	}
	
	
}
