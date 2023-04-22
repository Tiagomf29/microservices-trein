package com.uploadphotoperson.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uploadphotoperson.service.PersonPhotoService;

@RestController
@RequestMapping("/person")
public class PersonPhotoController {

	@Autowired
	private PersonPhotoService personPhotoService;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "photos/{cpf}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void insertUpdatePhotoPerson(@PathVariable String cpf, @RequestParam MultipartFile photo) throws IOException, SQLException {
		byte[] arqPhoto = photo.getInputStream().readAllBytes();
		personPhotoService.insertUpdatePhotoPerson(cpf, arqPhoto);
	}
	
	@GetMapping("/photos/{cpf}")
	public byte[] listPhotoPerson(@PathVariable String cpf) throws IOException, SQLException {
		return personPhotoService.listPhotoPerson(cpf);
	}
	
}
