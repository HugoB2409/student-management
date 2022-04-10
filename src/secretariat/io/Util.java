package secretariat.io;

import java.util.Collection;

import secretariat.Cours;
import secretariat.Etudiant;
import secretariat.Inscription;
import secretariat.TableauPrincipal;
import secretariat.io.reader.CoursReader;
import secretariat.io.reader.EtudiantReader;
import secretariat.io.reader.InscriptionReader;
import secretariat.io.reader.Reader;
import secretariat.io.writer.CoursWriter;
import secretariat.io.writer.EtudiantWriter;
import secretariat.io.writer.InscriptionWriter;
import secretariat.io.writer.Writer;

/**
 * Pour les modules de lecture-écriture, c'est à vous de les créer. Il
 * <b>faut</b> les référencer ici pour qu'on puisse les tester à la correction.
 *
 */
public class Util {

	public static Reader<Collection<Etudiant>> getEtudiantReader() {
		return new EtudiantReader();
	}

	public static Writer<Iterable<Etudiant>> getEtudiantWriter() {
		return new EtudiantWriter();
	}

	public static Reader<Collection<Cours>> getCoursReader() {
		return new CoursReader();
	}

	public static Writer<Iterable<Cours>> getCoursWriter() {
		return new CoursWriter();
	}

	/**
	 * Pour la lecture des {@link Inscription Inscriptions}, il faut une visibilité
	 * vers les {@link Cours} et {@link Etudiant Étudiants} du
	 * {@link TableauPrincipal}.
	 */
	public Reader<Collection<Inscription>> getInscriptionReader(TableauPrincipal tableau) {
		return new InscriptionReader(tableau);
	}

	public Writer<Iterable<Inscription>> getInscriptionWriter() {
		return new InscriptionWriter();
	}

}
