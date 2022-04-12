package junit.junit.correction.baseTests;

import static junit.junit.util.DataMaker.makeDefaultCours;
import static junit.junit.util.DataMaker.makeDefaultEtudiants;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import secretariat.Cours;
import secretariat.Etudiant;
import secretariat.TableauPrincipal;

/**
 * Test de base du tableau principal
 * 
 * @author Louis Hamel
 *
 */
class BaseTestAjoutCrsEtdsTableauPrincipal {

	// TODO
	private TableauPrincipal tableauVide = new TableauPrincipal();

	private Iterable<Cours> cours_a_ajouter;
	private Iterable<Etudiant> etudiants_a_ajouter;

	@BeforeEach
	void setUp() throws Exception {

		cours_a_ajouter = makeDefaultCours();
		etudiants_a_ajouter = makeDefaultEtudiants();

	}

	@AfterEach
	void tearDown() throws Exception {

		tableauVide = null;
		cours_a_ajouter = null;
		etudiants_a_ajouter = null;

	}

	@Test
	void testAjouterEtudiant() {

		List<Etudiant> etudiantsAjoutes = new ArrayList<>();

		for (Etudiant etudiantAjout : etudiants_a_ajouter) {

			tableauVide.ajouterEtudiant(etudiantAjout);
			etudiantsAjoutes.add(etudiantAjout);

			for (Etudiant etudiantDejaAjoute : etudiantsAjoutes) {

				// On vérifie que les étudiants déjà ajoutés sont encore là
				assertTrue(contains(tableauVide.getEtudiants(), etudiantDejaAjoute));
			}

		}

	}

	@Test
	void testAjouterCours() {

		List<Cours> coursAjoutes = new ArrayList<>();

		for (Cours coursAjout : cours_a_ajouter) {

			tableauVide.ajouterCours(coursAjout);
			coursAjoutes.add(coursAjout);

			for (Cours coursDejaAjoute : coursAjoutes) {

				// On vérifie que les cours déjà ajoutés sont encore là
				assertTrue(contains(tableauVide.getCours(), coursDejaAjoute));
			}

		}

	}

	private void ajouterCoursEtEtudiants() {

		for (Cours cours : cours_a_ajouter) {
			tableauVide.ajouterCours(cours);
		}

		for (Etudiant etudiant : etudiants_a_ajouter) {
			tableauVide.ajouterEtudiant(etudiant);
		}

	}

	/**
	 * Vérifie que le tableau est vide au départ et qu'il n'est pas vide suite auz ajouts
	 */
	@Test
	void testGetCours() {
		Iterable<Cours> iterCours = tableauVide.getCours();

		assertFalse(iterCours.iterator().hasNext());
		assertFalse(iterCours.iterator() == iterCours.iterator());

		ajouterCoursEtEtudiants();

		iterCours = tableauVide.getCours();

		assertTrue(iterCours.iterator().hasNext());

	}

	@Test
	void testGetEtudiants() {

		Iterable<Etudiant> iterEtud = tableauVide.getEtudiants();

		assertFalse(iterEtud.iterator().hasNext());
		assertFalse(iterEtud.iterator() == iterEtud.iterator());

		ajouterCoursEtEtudiants();

		iterEtud = tableauVide.getEtudiants();

		assertTrue(iterEtud.iterator().hasNext());

	}

	private <T> boolean contains(Iterable<T> iterable, T obj) {
		if (obj == null)
			return false;

		for (T t : iterable) {
			if (obj.equals(t))
				return true;
		}
		return false;
	}

}
