package secretariat.exception;

import java.io.Serial;

/**
 * Sert à s'indiquer qu'un bout de code n'est pas implémenté.
 * <p>
 * <p>
 * Les {@link RuntimeException}s ne nécessite pas d'ajouter <b>throws</b> à la
 * signature de la méthode qui la lance.
 */
@Deprecated
public class NotImplementedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotImplementedException() {
        super("Pas implémenté");
    }

}
