package br.com.ucsal.meta.agil.exception;

public class UserExistingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserExistingException(String msg) {
		super(msg);
	}
}
