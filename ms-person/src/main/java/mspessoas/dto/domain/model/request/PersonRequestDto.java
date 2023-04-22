package mspessoas.dto.domain.model.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mspessoas.domain.model.Nationality;

@Getter @Setter
public class PersonRequestDto {
	
	@NotBlank
	@Size(min = 4, max = 80)
	private String nome;
	private LocalDate nascimento;
	
	@Size(max = 80)
	private String nomeMae;
	
	@Size(max = 80)
	private String nomePai;
	
	private Nationality nationality;
	
	@NotBlank
	@Size(min = 11, max = 11)
	private String cpf;
}
