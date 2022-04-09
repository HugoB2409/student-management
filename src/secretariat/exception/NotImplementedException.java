package secretariat.exception;

/**
 * 
 * Sert à s'indiquer qu'un bout de code n'est pas implémenté.
 *
 * 
 * Les {@link RuntimeException}s ne nécessite pas d'ajouter <b>throws</b> à la
 * signature de la méthode qui la lance.
 */
@Deprecated
public class NotImplementedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotImplementedException() {
		super("Pas implémenté");
	}

}
