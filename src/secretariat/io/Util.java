package secretariat.io;

import java.util.Collection;

import secretariat.Cours;
import secretariat.Etudiant;
import secretariat.Inscription;
import secretariat.TableauPrincipal;
import secretariat.exception.NotImplementedException;
import secretariat.io.reader.CoursReader;
import secretariat.io.reader.EtudiantReader;
import secretariat.io.reader.InscriptionReader;
import secretariat.io.reader.Reader;
import secretariat.io.writer.Writer;

/**
 * Pour les modules de lecture-écriture, c'est à vous de les créer. Il
 * <b>faut</b> les référencer ici pour qu'on puisse les tester à la correction.
 *
 *//**
 * Pour les modules de lecture-écriture, c'est à vous de les créer. Il
 * <b>faut</b> les référencer ici pour qu'on puisse les tester à la correction.
 *
 */
public class Util {

	public static Reader<Collection<Etudiant>> getEtudiantReader() {
		return new EtudiantReader();
	}

	public static Writer<Iterable<Etudiant>> getEtudiantWriter() {
		throw new NotImplementedException();
	}

	public static Reader<Collection<Cours>> getCoursReader() {
		return new CoursReader();
	}

	public static Writer<Iterable<Cours>> getCoursWriter() {
		throw new NotImplementedException();
	}

	/**
	 * Pour la lecture des {@link Inscription Inscriptions}, il faut une visibilité
	 * vers les {@link Cours} et {@link Etudiant Étudiants} du
	 * {@link TableauPrincipal}.
	 * 
	 * @param tableau
	 * @return
	 */	/**
	 * Pour la lecture des {@link Inscription Inscriptions}, il faut une visibilité
	 * vers les {@link Cours} et {@link Etudiant Étudiants} du
	 * {@link TableauPrincipal}.
	 *
	 * @param tableau
	 * @return
	 */
	public Reader<Collection<Inscription>> getInscriptionReader(TableauPrincipal tableau) {
		return new InscriptionReader();
	}

	public Writer<Iterable<Inscription>> getInscriptionWriter() {
		throw new NotImplementedException();
	}

}
