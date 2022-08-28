package br.com.almanak.almanakApi.exception;

public class LoginNotFoundException  extends Exception{
    
    public LoginNotFoundException() {
		super("Login não encontrado.");
	}
	
	public LoginNotFoundException(String msg) {
		super(msg);
	}

}
