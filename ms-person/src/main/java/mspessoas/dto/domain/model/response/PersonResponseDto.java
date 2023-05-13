package mspessoas.dto.domain.model.response;

import lombok.Getter;
import lombok.Setter;
import mspessoas.domain.model.Nationality;

@Getter @Setter
public class PersonResponseDto {

	private Integer id;
	private String nome;
	private String nascimento;
	private String nomeMae;
	private String nomePai;
	private String cpf;
	
	private Nationality nationality;
}
