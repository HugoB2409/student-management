package junit.junit.correction.testsAvances;

import static junit.junit.correction.baseTests.BaseTestInscriptions.collectInscriptions;
import static junit.junit.util.DataMaker.makeDefaultTableau;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import secretariat.Cours;
import secretariat.Etudiant;
import secretariat.Inscription;
import secretariat.TableauPrincipal;
import secretariat.io.Util;
/**
 * Vérifie que le tableau reste cohérent après la lecture.
 * 
 * @author Louis Hamel
 *
 */
class TestInscriptionsPostLecture {

	@BeforeEach
	protected void setUp() throws Exception {

	}

	@Test
	void test() throws IOException {

		TableauPrincipal tableau = makeDefaultTableau(true);

		File f = new File("ioTest/AdvancedEtudiants.txt");

		Util.getEtudiantWriter().write(tableau.getEtudiants(), f);
		Collection<Etudiant> stds = Util.getEtudiantReader().read(f);

		f = new File("ioTest/AdvancedCours.txt");

		Util.getCoursWriter().write(tableau.getCours(), f);
		Collection<Cours> lesCours = Util.getCoursReader().read(f);

		f = new File("ioTest/AdvancedInscriptions.txt");

		tableau = new TableauPrincipal();
		addAll(tableau, lesCours, stds);

		TestAjoutDouble.assertCoherence(tableau);
		
		new Util().getInscriptionWriter().write(collectInscriptions(tableau), f);
		Collection<Inscription> inscriptions = new Util().getInscriptionReader(tableau).read(f);

		if (collectInscriptions(tableau).size() == 0) {
			for (Inscription inscription : inscriptions) {
				tableau.inscrire(inscription.getCours().getSigle(), inscription.getEtudiant().getCodePermanent());
			}
		} else {
			System.out.println("Les inscriptions ont été réalisées lors de la lecture");
		}

		TestAjoutDouble.assertCoherence(tableau);

	}

	public void addAll(TableauPrincipal tab, Iterable<Cours> lesCours, Iterable<Etudiant> stds) {

		for (Etudiant etudiant : stds) {
			tab.ajouterEtudiant(etudiant);
		}

		for (Cours cours : lesCours) {
			tab.ajouterCours(cours);
		}

	}

}
