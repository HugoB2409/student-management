package junit.junit.correction.baseTests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import secretariat.Cours;
import secretariat.Etudiant;
import secretariat.Inscription;
import secretariat.TableauPrincipal;

import static junit.junit.util.DataMaker.makeDefaultCours;
import static junit.junit.util.DataMaker.makeDefaultEtudiants;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests de la classe Inscription. Vérifie aussi la consistance du chaînage.
 * 
 * @author Louis Hamel
 *
 */
public class BaseTestInscriptions {

	private TableauPrincipal tableau;

	private Collection<Etudiant> etudiants;
	private Collection<Cours> lesCours;

	private String[][] indexInscriptions;

	@BeforeEach
	protected void setUp() throws Exception {

		tableau = new TableauPrincipal();

		etudiants = makeDefaultEtudiants();
		lesCours = makeDefaultCours();

		for (Etudiant etud : etudiants) {
			tableau.ajouterEtudiant(etud);
		}

		for (Cours cours : lesCours) {
			tableau.ajouterCours(cours);
		}

		indexInscriptions = new String[][] { { "LIP1006", "POTJ19502563" }, { "LIP1009", "POTJ19502563" },
				{ "LIP1006", "MANB18699503" }, { "MAP1062", "MANB18699503" }, { "MAP1062", "JOHJ19356214" } };

	}

//	protected void setUp(TableauPrincipal tb, Collection<Etudiant> stds, Collection<Cours> crs) {
//		
//	}

	@AfterEach
	void tearDown() throws Exception {
		tableau = null;
		etudiants = null;
		lesCours = null;
		indexInscriptions = null;

	}

//	protected TableauPrincipal getTableau() {
//		return tableau;
//	}

	protected void assertCoherence(TableauPrincipal tableau) {
		assertRelatedInscriptions(tableau);
		assertInscriptionsInBothLists(tableau);
	}

	@Test
	void testInscrireCoursEtudiant() {

		for (int i = 0; i < indexInscriptions.length; i++) {

			final int index = i;

			Cours cours = get(lesCours, (crs) -> indexInscriptions[index][0].equals(crs.getSigle()));
			Etudiant etudiant = get(etudiants, (etd) -> indexInscriptions[index][1].equals(etd.getCodePermanent()));

			// Lorsqu'on fait une inscription
			boolean resultat = tableau.inscrire(cours, etudiant);

			String cpEtudiant = etudiant.getCodePermanent();
			String sigleCours = cours.getSigle();
			Cours coursExtrait = get(tableau.getCours(), (crs) -> crs.getSigle().equals(sigleCours));
			Etudiant etudiantExtrait = get(tableau.getEtudiants(), (etd) -> etd.getCodePermanent().equals(cpEtudiant));

			// L'inscription se retrouve dans la liste chainée du cours et de l'étudiant

			assertTrue(contains(() -> coursExtrait.iterator(),
					(inscription) -> inscription.getEtudiant().getCodePermanent().equals(cpEtudiant)));

			// Idem pour les cours
			assertTrue(contains(() -> etudiantExtrait.iterator(),
					(inscription) -> inscription.getCours().getSigle().equals(sigleCours)));

			// On vérifie aussi que pour chaque étudiant, les inscriptions de sa liste
			// correspondent à cet étudiant seulement.
			assertCoherence(tableau);

			// On vérifie que la réponse était positive
			assertTrue(resultat);
		}
	}

	/**
	 * Assure que pour chaque étudiant, les inscriptions de sa liste sont reliés à
	 * cet étudiant. Idem pours les cours
	 * 
	 * @param tab
	 */
	public static void assertRelatedInscriptions(TableauPrincipal tab) {

		for (Etudiant etudiant : tab.getEtudiants()) {
			Iterable<Inscription> listeInscriptions = etudiant::iterator;
			for (Inscription inscription : listeInscriptions) {
				assertTrue(etudiant == inscription.getEtudiant());
			}

		}

		for (Cours cours : tab.getCours()) {
			Iterable<Inscription> listeInscriptions = cours::iterator;

			for (Inscription inscription : listeInscriptions) {
				assertTrue(cours == inscription.getCours());
			}
		}

	}

	/**
	 * Assure que toute inscription dans la liste des inscriptions d'un cours sont
	 * présentes dans la liste d'inscription de l'étudiant associé. Et vice versa.
	 * 
	 * @param tab
	 */
	public static void assertInscriptionsInBothLists(TableauPrincipal tab) {

		for (Cours cours : tab.getCours()) {// Pour chaque cours

			Iterable<Inscription> listeInscriptions = cours::iterator;

			for (Inscription inscription : listeInscriptions) {// Pour chaque inscription de ce cours

				// L'étudiant contient l'inscription dans sa liste

				contains(inscription.getEtudiant()::iterator,
						(inscrip) -> inscrip.getEtudiant() == inscription.getEtudiant());
			}

		}

		for (Etudiant etud : tab.getEtudiants()) {// Pour chaque Etudiant

			Iterable<Inscription> listeInscriptions = etud::iterator;

			for (Inscription inscription : listeInscriptions) {// Pour chaque inscription de cet etudiant

				// Le cours contient l'inscription dans sa liste

				contains(inscription.getCours()::iterator,
						(inscrip) -> inscrip.getCours() == inscription.getCours());
			}

		}

	}

	@Test
	void testInscrireStringString() {

		for (int i = 0; i < indexInscriptions.length; i++) {

			final int index = i;

			// Lorsqu'on fait une inscription
			tableau.inscrire(indexInscriptions[i][0], indexInscriptions[i][1]);

			Cours cours = get(lesCours, (crs) -> indexInscriptions[index][0].equals(crs.getSigle()));
			Etudiant etudiant = get(etudiants, (etd) -> indexInscriptions[index][1].equals(etd.getCodePermanent()));

			String cpEtudiant = etudiant.getCodePermanent();
			String sigleCours = cours.getSigle();
			Cours coursExtrait = get(tableau.getCours(), (crs) -> crs.getSigle().equals(sigleCours));
			Etudiant etudiantExtrait = get(tableau.getEtudiants(), (etd) -> etd.getCodePermanent().equals(cpEtudiant));

			// L'inscription se retrouve dans la liste chainée du cours et de l'étudiant

			assertTrue(contains(() -> coursExtrait.iterator(),
					(inscription) -> inscription.getEtudiant().getCodePermanent().equals(cpEtudiant)));

			// Idem pour les cours
			assertTrue(contains(() -> etudiantExtrait.iterator(),
					(inscription) -> inscription.getCours().getSigle().equals(sigleCours)));

			// On vérifie aussi que pour chaque étudiant, les inscriptions de sa liste
			// correspondent à cet étudiant seulement.
			assertCoherence(tableau);

		}

	}

	public static List<Inscription> collectInscriptions(TableauPrincipal tab) {

		ArrayList<Inscription> list = new ArrayList<>();

		for (Cours cours : tab.getCours()) {

			Iterable<Inscription> iterationsDuCours = cours::iterator;
			for (Inscription inscription : iterationsDuCours) {
				list.add(inscription);
			}

		}

		return list;

	}

	@Test
	void testDesinscrireCoursEtudiant() {
		// On réalise l'inscription avec le test qui le fait.
		testInscrireCoursEtudiant();

		List<Inscription> inscriptGardees = collectInscriptions(tableau);

		List<Inscription> retrait = new ArrayList<Inscription>();

		// Sélection des inscriptions à retirer
		for (int i = 0; i < indexInscriptions.length; i += 2) {
			retrait.add(inscriptGardees.get(i));
		}

		// Retrait des inscriptions gardées
		inscriptGardees.removeAll(retrait);

		// Réalisation de la désinscription
		for (Inscription inscription : retrait) {

			Cours coursRelie = inscription.getCours();
			Etudiant etudiantRelie = inscription.getEtudiant();

			tableau.desinscrire(coursRelie, etudiantRelie);

			// Valider absence de l'inscription retirées dans la liste de l'étudiant et du
			// cours
			assertFalse(contains(coursRelie::iterator, (inscripCours) -> inscripCours == inscription));
			assertFalse(contains(etudiantRelie::iterator, (inscripEtud) -> inscripEtud == inscription));

		}

		// Assurer que les inscriptions non effacées sont toujours présentes
		List<Inscription> inscriptionRestantes = collectInscriptions(tableau);
		inscriptionRestantes.containsAll(inscriptGardees);

	}

	@Test
	void testDesinscrireStringString() {

		// On réalise l'inscription avec le test qui le fait.
		testInscrireCoursEtudiant();

		List<Inscription> inscriptGardees = collectInscriptions(tableau);

		List<Inscription> retrait = new ArrayList<Inscription>();

		// Sélection des inscriptions à retirer
		for (int i = 0; i < indexInscriptions.length; i += 2) {
			retrait.add(inscriptGardees.get(i));
		}

		// Retrait des inscriptions gardées
		inscriptGardees.removeAll(retrait);

		// Réalisation de la désinscription
		for (Inscription inscription : retrait) {

			Cours coursRelie = inscription.getCours();
			Etudiant etudiantRelie = inscription.getEtudiant();

			tableau.desinscrire(coursRelie.getSigle(), etudiantRelie.getCodePermanent());

			// Valider absence de l'inscription retirées dans la liste de l'étudiant et du
			// cours
			assertFalse(contains(coursRelie::iterator, (inscripCours) -> inscripCours == inscription));
			assertFalse(contains(etudiantRelie::iterator, (inscripEtud) -> inscripEtud == inscription));

		}

		// Assurer que les inscriptions non effacées sont toujours présentes
		List<Inscription> inscriptionRestantes = collectInscriptions(tableau);
		inscriptionRestantes.containsAll(inscriptGardees);

	}

	public static <T> boolean contains(Iterable<T> iterable, Predicate<T> pred) {

		for (T t : iterable) {
			if (pred.test(t))
				return true;
		}

		return false;
	}

	public static <T> T get(Iterable<T> iterable, Predicate<T> pred) {

		for (T t : iterable) {
			if (pred.test(t))
				return t;
		}

		return null;

	}

}
