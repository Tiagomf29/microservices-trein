package mspessoas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cpf já existe para outra pessoa.")
public class ExceptionCpfExists extends Exception{

	private static final long serialVersionUID = 1L;


}
