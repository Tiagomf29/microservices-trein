package mspessoas.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter @Setter
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 80, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private LocalDate nascimento;
	
	@Column(length = 80, nullable = true)
	private String nomeMae;
	
	@Column(length = 80, nullable = true)
	private String nomePai;
	
	@Column(length = 14, nullable = false)
	private String cpf;
	
	@ManyToOne
	@JoinColumn(name = "id_nationality")
	private Nationality nationality;
}
