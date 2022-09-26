package br.com.pb.projeto.api.estados.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException ex) {
		
		List<ErroDeFormularioDto> erros = new ArrayList<ErroDeFormularioDto>();
		
		List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
		
		for(FieldError erro : fieldError) {
			ErroDeFormularioDto erroDto = new ErroDeFormularioDto(erro.getField(), erro.getDefaultMessage());
			erros.add(erroDto);
		}
		return erros; 
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST) 
	@ExceptionHandler(InvalidFormatException.class) 
	public ErroDeFormularioDto handle(InvalidFormatException ex) {
		
		if(ex.getPath().toString().contains("regiao")) {
			
			String campo = "Regiao";
			String mensagem = "deve ser Norte, Nordeste, Sul, Sudeste ou Centro_Oeste";
			
			ErroDeFormularioDto erro = new ErroDeFormularioDto(campo, mensagem);
			return erro;
			
		} else if(ex.getMessage().toString().contains("not a valid")) {
			
			int tamanho = ex.getPath().toString().length();
			String campo = ex.getPath().toString().substring(59, tamanho-3);
			String mensagem = "deve conter um valor valido para o campo";
			
			ErroDeFormularioDto erro = new ErroDeFormularioDto(campo, mensagem);
			return erro;
			
		}
		
		int tamanho = ex.getPath().toString().length();
		String campo = ex.getPath().toString().substring(59, tamanho-3);
		ErroDeFormularioDto erro = new ErroDeFormularioDto(campo, ex.getMessage());
		return erro; 	
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmailExistenteException.class)
	public ErroDeFormularioDto handler(EmailExistenteException exception) {
		ErroDeFormularioDto erro = new ErroDeFormularioDto("email", exception.getMessage());
		return erro;
	}
}
