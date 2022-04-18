package secretariat.exception;

import java.io.Serial;

public class BadInstanciationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BadInstanciationException() {
        super();
    }

    public BadInstanciationException(String message) {
        super(message);
    }
}
