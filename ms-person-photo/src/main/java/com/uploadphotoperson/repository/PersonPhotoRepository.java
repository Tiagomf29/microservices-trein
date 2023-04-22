package com.uploadphotoperson.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uploadphotoperson.model.PersonPhoto;

public interface PersonPhotoRepository extends JpaRepository<PersonPhoto, Integer>{

	Optional<PersonPhoto> findByCpf(String cpf);
	
}
