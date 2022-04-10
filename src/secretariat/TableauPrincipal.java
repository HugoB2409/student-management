package secretariat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import secretariat.exception.NotImplementedException;
import secretariat.io.Util;

/**
 * Le tableau principal contient la liste des cours et la liste des étudiants.
 * Comme elle a une visibilité vers les deux listes, c'est cette classe qui aura
 * la responsabilité de gérer l'ajout/retrait d'une inscription.
 *
 */
public class TableauPrincipal {

	private List<Cours> listeCours;
	private List<Etudiant> listeEtudiants;
	private List<Inscription> listeInscriptions;

	public TableauPrincipal() {}

	public void initFromFiles() {
		Util util = new Util();
		listeCours = (List<Cours>) Util.getCoursReader().read(new File("data/Cours.txt"));
		listeEtudiants = (List<Etudiant>) Util.getEtudiantReader().read(new File("data/Etudiants.txt"));
		listeInscriptions = (List<Inscription>) util.getInscriptionReader(this).read(new File("data/Inscriptions.txt"));
	}

	public void saveModifications() {

	}

	public List<Cours> findCoursByStudent(String codePermanent) {
		ArrayList<Cours> cours = new ArrayList<Cours>();
		listeInscriptions.stream().filter(inscription -> inscription.getEtudiant().getCodePermanent().equals(codePermanent)).forEach(inscription -> cours.add(inscription.getCours()));
		return cours;
	}

	public List<Etudiant> findStudentByCours(String sigle) {
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		listeInscriptions.stream().filter(inscription -> inscription.getCours().getSigle().equals(sigle)).forEach(inscription -> etudiants.add(inscription.getEtudiant()));
		return etudiants;
	}

	public Etudiant getEtudiant(String codePermanent) {
		if (listeEtudiants == null)
			return null;

		for (Etudiant etudiant: listeEtudiants) {
			if (etudiant.getCodePermanent().equals(codePermanent)) {
				return etudiant;
			}
		}
		return null;
	}

	public Cours getCour(String sigle) {
		if (listeCours == null)
			return null;

        for (Cours cours: listeCours) {
            if (cours.getSigle().equals(sigle)) {
                return cours;
            }
        }
        return null;
	}

	public void ajouterEtudiant(Etudiant etud) {
		throw new NotImplementedException();
	}

	public void ajouterCours(Cours cours) {
		throw new NotImplementedException();
	}

	/**
	 * Réalise l'inscription d'un étudiant à un cours. L'étudiant et le cours
	 * <b>DOIVENT</b> être présents dans les listes pour pouvoir faire
	 * l'inscription.
	 *
	 * <p>
	 *
	 * <i>Conseil</i>: Que se passe-t-il si un objet reçu en argument est une copie
	 * d'un objet contenu dans une liste (même contenu, instances différentes) ?
	 * Gérez cette éventualité.
	 *
	 * @param cours L'instance du cours
	 * @param etud  L'instance de l'étudiant
	 * @return si l'inscription a pu être réalisée.
	 */
	public boolean inscrire(Cours cours, Etudiant etud) {
		throw new NotImplementedException();
	}

	/**
	 * Réalise l'inscription d'un étudiant à un cours. L'étudiant et le cours
	 * <b>DOIVENT</b> être présents dans les listes pour pouvoir faire
	 * l'inscription.
	 *
	 * <p>
	 *
	 * <i>Indice</i>: une fois les objets trouvés dans les listes, vous pouvez
	 * déléguer à l'autre méthode.
	 *
	 * @param sigle         Le sigle du cours
	 * @param codePermanent Le code permanent de l'étudiant
	 * @return si l'inscription a pu être réalisée.
	 */
	public boolean inscrire(String sigle, String codePermanent) {

		throw new NotImplementedException();
	}

	/**
	 * Réalise la désinscription d'un étudiant à un cours.
	 *
	 *
	 * <p>
	 *
	 * <i>Conseil</i>: Que se passe-t-il si un objet reçu en argument est une copie
	 * d'un objet contenu dans une liste (même contenu, instances différentes) ?
	 * Gérez cette éventualité.
	 *
	 * @param cours L'instance du cours
	 * @param etud  L'instance de l'étudiant
	 * @return si l'inscription a pu être réalisée.
	 */
	public boolean desinscrire(Cours cours, Etudiant etud) {
		throw new NotImplementedException();
	}

	/**
	 * Réalise la désinscription d'un étudiant à un cours.
	 *
	 *
	 * @param sigle L'instance du cours
	 * @param codePermanent  L'instance de l'étudiant
	 * @return si l'inscription a pu être réalisée.
	 */
	public boolean desinscrire(String sigle, String codePermanent) {
		throw new NotImplementedException();
	}

	public Iterable<Cours> getCours() {
		return listeCours;
	}

	public Iterable<Etudiant> getEtudiants() {
		return listeEtudiants;
	}
}
