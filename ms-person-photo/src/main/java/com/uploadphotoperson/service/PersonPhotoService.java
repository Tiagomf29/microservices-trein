package com.uploadphotoperson.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uploadphotoperson.model.PersonPhoto;
import com.uploadphotoperson.repository.PersonPhotoRepository;

@Service
public class PersonPhotoService {

	@Autowired
	private PersonPhotoRepository personPhotoRepository;
	
	public void insertUpdatePhotoPerson(String cpf, byte[] photo) throws SQLException {
		Blob blob = new SerialBlob(photo);
		Optional<PersonPhoto> pf = personPhotoRepository.findByCpf(cpf);
		if(pf.isPresent()) {				
			pf.get().setCpf(cpf);
			pf.get().setPhoto(blob);
			personPhotoRepository.save(pf.get());
		}else {
			PersonPhoto person = new PersonPhoto();
			person.setCpf(cpf);
			person.setPhoto(blob);
			personPhotoRepository.save(person);
		}
	}
	
	public byte[] listPhotoPerson(String cpf) throws IOException, SQLException {
		
		Optional<PersonPhoto> pf = personPhotoRepository.findByCpf(cpf);
		
		byte[] arquivo = null;
		
		if(pf.isPresent()) {
			arquivo =  pf.get().getPhoto().getBinaryStream().readAllBytes();
		}
		
		return arquivo;
	}
	
}
