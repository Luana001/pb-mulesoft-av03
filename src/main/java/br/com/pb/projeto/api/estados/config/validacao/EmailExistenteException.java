package br.com.pb.projeto.api.estados.config.validacao;

public class EmailExistenteException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public EmailExistenteException() {
		super("Este email já está sendo usado por outro usuário");
	}
	
}
