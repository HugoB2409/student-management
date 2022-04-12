package junit.junit.correction.baseTests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import secretariat.Cours;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de la classe Cours
 * 
 * @author Louis Hamel
 *
 */
class BaseTestCours {

	private Cours[] lesCours;
	private String[] sigles = { "LIP1006", "LIP1009", "LIP1015" };
	private String[] noms = { "Lipsum", "Latex", "Beamer" };
	private int[] maxEtudiants = { 30, 30, 25 };
	private List<List<Cours>> listePrerequis;

	@BeforeEach
	void setUp() throws Exception {

		// Instanciation
		lesCours = new Cours[3];
		for (int i = 0; i < lesCours.length; i++) {
			lesCours[i] = new Cours(sigles[i], noms[i], maxEtudiants[i], new ArrayList<>());
		}

		// Ajout des prerequis
		preparePrerequis();
		for (int i = 0; i < lesCours.length; i++) {
			lesCours[i].setPrerequis(listePrerequis.get(i));
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		lesCours = null;
		sigles = null;
		noms = null;
		maxEtudiants = null;
		listePrerequis = null;
	}

	private void preparePrerequis() {
		listePrerequis = new ArrayList<List<Cours>>();
		listePrerequis.add(new ArrayList<Cours>());// LIP1006
		listePrerequis.add(new ArrayList<Cours>());// LIP1009

		ArrayList<Cours> liste = new ArrayList<Cours>();
		liste.add(lesCours[1]);
		liste.add(lesCours[0]);

		listePrerequis.add(liste);// LIP1015

	}

	/**
	 * Vérifie que le constructeur ne plante pas.
	 */
	@Test
	void testCours() {
		new Cours(sigles[0], noms[0], maxEtudiants[0], new ArrayList<>());

	}

	@Test
	void testGetSigle() {

		for (int i = 0; i < lesCours.length; i++) {
			assertEquals(sigles[i], lesCours[i].getSigle());
		}
	}

	@Test
	void testGetNom() {
		for (int i = 0; i < lesCours.length; i++) {
			assertEquals(noms[i], lesCours[i].getNom());
		}
	}

	@Test
	void testGetMaxEtudiants() {
		for (int i = 0; i < lesCours.length; i++) {
			assertEquals(maxEtudiants[i], lesCours[i].getMaxEtudiants());
		}
	}

	@Test
	void testGetNbEtudiant() {
		for (Cours cours : lesCours) {
			assertEquals(0, cours.getNbEtudiant());
		}
	}

	@Test
	void testGetPrerequis() {
		for (int i = 0; i < lesCours.length; i++) {

			for (Cours prerequis : listePrerequis.get(i)) {
				assertTrue(lesCours[i].getPrerequis().contains(prerequis));
			}

			// On vérifie que le nombre de prérequis est le même
			assertEquals(listePrerequis.get(i).size(), lesCours[i].getPrerequis().size());

		}
	}

	@Test
	void testGetIterateurInscription() {

		for (Cours cours : lesCours) {
			//Il ne devrait pas y avoir d'inscription dès le départ
			assertFalse(cours.iterator().hasNext());

			// Chaque invocation devrait retourner une nouvelle instance
			assertFalse(cours.iterator() == cours.iterator());
		}
	}

	

}
