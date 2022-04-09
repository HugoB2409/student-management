package secretariat.io.reader;

import java.io.File;

/**
 * 
 * Interface de lecture
 *
 * @param <T>
 */
public interface Reader<T> {

	public abstract T read(File file);

}
