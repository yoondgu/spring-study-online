package kr.co.hta.exception;

public class OnlineApplicationException extends RuntimeException {

	private static final long serialVersionUID = 7299015771786044145L;

	public OnlineApplicationException(String message) {
		super(message);
	}
	
	public OnlineApplicationException(String message, Throwable cause) {
		super(message, cause);
	}
}
