package junit.junit.correction.baseTests;


import static junit.junit.correction.baseTests.BaseTestInscriptions.contains;
import static junit.junit.util.DataMaker.makeDefaultTabEtudCours;
import static junit.junit.util.DataMaker.makeDefaultTableau;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import secretariat.Cours;
import secretariat.Etudiant;
import secretariat.Inscription;
import secretariat.TableauPrincipal;
import secretariat.io.Util;
import secretariat.io.reader.Reader;
import secretariat.io.writer.Writer;

/**
 * Test de la lecture et de l'écriture
 * 
 * @author Louis Hamel
 *
 */
class BaseTestReadWrite {

	private static boolean useStrings = false;

	static {
		BaseTestInscriptions test = new BaseTestInscriptions();

		try {
			test.setUp();

			test.testInscrireCoursEtudiant();

			useStrings = true;

			test.tearDown();

		} catch (Exception | AssertionError e) {
			useStrings = false;
		}

	}

	private TableauPrincipal tableauRempli;

	@BeforeAll
	static void setupClass() {
		// On efface les fichiers présents
		File testDir = new File("./ioTest");

		System.out.println("BeforeClass");

		for (File file : testDir.listFiles((f) -> f.isFile())) {
			file.delete();
		}

		assert testDir.listFiles().length == 0;

	}

	@BeforeEach
	void setUp() throws Exception {

		tableauRempli = makeDefaultTableau(useStrings);
	}

	@AfterEach
	void tearDown() throws Exception {
		tableauRempli = null;
	}

	@Test
	void testGetEtudiantReader() throws IOException {
		File f = new File("ioTest/Etudiants.txt");

		Iterable<Etudiant> etudiants = tableauRempli.getEtudiants();

		Writer<Iterable<Etudiant>> writer = Util.getEtudiantWriter();
		Reader<Collection<Etudiant>> reader = Util.getEtudiantReader();

		writer.write(etudiants, f);

		if (!f.exists())
			fail("Le fichier n'a pas été créé");

		etudiants = makeDefaultTableau(useStrings).getEtudiants();

		Collection<Etudiant> etudiantsLus = reader.read(f);

		assertTrue(allInAll(etudiants, etudiantsLus, BaseTestReadWrite::comparateurEtudiant));

	}

	@Test
	void testGetCoursReader() throws IOException {
		File f = new File("ioTest/Cours.txt");

		Iterable<Cours> lesCours = tableauRempli.getCours();

		Writer<Iterable<Cours>> writer = Util.getCoursWriter();
		Reader<Collection<Cours>> reader = Util.getCoursReader();

		writer.write(lesCours, f);

		if (!f.exists())
			fail("Le fichier n'a pas été créé");

		lesCours = makeDefaultTableau(useStrings).getCours();

		Collection<Cours> lesCoursLus = reader.read(f);

		assertTrue(allInAll(lesCours, lesCoursLus, BaseTestReadWrite::comparateurCours));

	}

	@Test
	void testGetInscriptionReader() throws IOException {

		File f = new File("ioTest/Inscriptions.txt");

		Util util = new Util();

		List<Inscription> inscriptions = BaseTestInscriptions.collectInscriptions(tableauRempli);

		TableauPrincipal tableauSansInscrip = makeDefaultTabEtudCours();

		Writer<Iterable<Inscription>> writer = util.getInscriptionWriter();
		Reader<Collection<Inscription>> reader = util.getInscriptionReader(tableauSansInscrip);

		writer.write(inscriptions, f);

		if (!f.exists())
			fail("Le fichier n'a pas été créé");

		// Recréation des inscriptions au cas où le reader modifierait l'état de la
		// liste
		inscriptions = BaseTestInscriptions.collectInscriptions(makeDefaultTableau(useStrings));

		Collection<Inscription> inscriptLues = reader.read(f);

		assertTrue(allInAll(inscriptions, inscriptLues, BaseTestReadWrite::comparateurInscriptions));

	}

	public static <T> boolean allInAll(Iterable<T> former, Iterable<T> latter, PredicateOnTwo<T> comparateur) {

		// On vérifie que toutes les inscriptions de l'une sont dans l'autre
		for (T t : former) {
			if (!contains(latter, comparateur.reduce(t)))
				return false;
		}

		for (T t : latter) {
			if (!contains(former, comparateur.reduce(t)))
				return false;
		}

		return true;
	}

	public static Predicate<Inscription> comparateurInscriptions(Inscription first) {
		return (second) -> {

			boolean equals = true;
			equals &= first.getEtudiant().getCodePermanent().equals(second.getEtudiant().getCodePermanent());
			equals &= first.getCours().getSigle().equals(second.getCours().getSigle());

			return equals;
		};
	}

	public static Predicate<Cours> comparateurCours(Cours first) {
		return second -> first.getSigle().equals(second.getSigle());
	}

	public static Predicate<Etudiant> comparateurEtudiant(Etudiant first) {
		return second -> first.getCodePermanent().equals(second.getCodePermanent());
	}

	interface PredicateOnTwo<T> {
		public Predicate<T> reduce(T elem);
	}

}
