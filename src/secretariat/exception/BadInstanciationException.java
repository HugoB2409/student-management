package secretariat.exception;

public class BadInstanciationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadInstanciationException() {
		super();
	}

	public BadInstanciationException(String message) {
		super(message);
	}
}
