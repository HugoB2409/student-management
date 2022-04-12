package junit.junit.util;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;

import secretariat.Cours;
import secretariat.Etudiant;
import secretariat.TableauPrincipal;

/**
 * Génération des données de test.
 * 
 * @author Louis Hamel
 *
 */
public class DataMaker {

	public static Collection<Etudiant> makeDefaultEtudiants() {

		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();

		etudiants.add(new Etudiant("POTJ19502563", "Potter", "James", 6033, 65));
		etudiants.add(new Etudiant("MEUM19303265", "Meurseault", "Monsieur", 6033, 23));
		etudiants.add(new Etudiant("JOHJ19356214", "Johnson", "John", 6033, 30));
		etudiants.add(new Etudiant("MANB18699503", "Mandelbrot", "Benoit", 6033, 65));
		etudiants.add(new Etudiant("NEWI35695623", "Newton", "Isaac", 6033, 75));

		return etudiants;
	}

	public static Collection<Cours> makeDefaultCours() {
		ArrayList<Cours> cours = new ArrayList<Cours>();

		cours.add(new Cours("LIP1006", "Lipsum", 30, new ArrayList<>()));
		cours.add(new Cours("LIP1009", "Latex", 30, new ArrayList<>()));
		cours.add(new Cours("LIP1015", "Beamer", 25, new ArrayList<>()));
		cours.add(new Cours("INF1004", "Structures de donnees", 50, new ArrayList<>()));
		cours.add(new Cours("MAP1062", "Geometrie euclidienne", 50, new ArrayList<>()));

		return cours;

	}

	/**
	 * Crée un tableau avec étudiants et cours, mais sans inscripitons.
	 * 
	 * @return
	 */
	public static TableauPrincipal makeDefaultTabEtudCours() {
		TableauPrincipal tableau = new TableauPrincipal();

		for (Etudiant etud : makeDefaultEtudiants()) {
			tableau.ajouterEtudiant(etud);
		}

		for (Cours cours : makeDefaultCours()) {
			tableau.ajouterCours(cours);
		}
		return tableau;
	}

	public static TableauPrincipal makeDefaultTableau(boolean inscrireString) {

		Scripteur inscripteur = inscrireString
				? (tableau, cours, etudiant) -> tableau.inscrire(cours.getSigle(), etudiant.getCodePermanent())
				: (tableau, cours, etud) -> tableau.inscrire(cours, etud);

		TableauPrincipal tableau = makeDefaultTabEtudCours();

		// Ajouter des inscriptions

		Iterable<Cours> listeCours = tableau.getCours();
		Iterable<Etudiant> listeEtudiant = tableau.getEtudiants();

		int i = 0;

		for (Cours crs : listeCours) {
			if (i++ % 2 == 1)
				continue;

			int j = 0;
			for (Etudiant std : listeEtudiant) {

				if (j++ % 2 == 1)
					continue;

				inscripteur.script(tableau, crs, std);

			}

		}

		return tableau;
	}

	public static ArrayList<SimpleEntry<Cours, Etudiant>> prepareInscriptions() {
		return prepareInscriptions(makeDefaultTableau(true));
	}

	public static ArrayList<SimpleEntry<Cours, Etudiant>> prepareInscriptions(TableauPrincipal tableau) {
		ArrayList<SimpleEntry<Cours, Etudiant>> inscriptions = new ArrayList<>();

		Iterable<Etudiant> etudiants = tableau.getEtudiants();
		Iterable<Cours> lesCours = tableau.getCours();

		int compteurEt = 0, compteurCours = 0;

		for (Cours cours : lesCours) {
			if (compteurCours++ % 3 == 1)
				continue;

			for (Etudiant std : etudiants) {
				if (compteurEt++ + compteurCours % 4 == 1)
					continue;

				inscriptions.add(new SimpleEntry<Cours, Etudiant>(cours, std));
			}
		}

		return inscriptions;
	}

	interface Scripteur {

		boolean script(TableauPrincipal tableau, Cours cours, Etudiant etud);
	}

}
