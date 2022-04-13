package secretariat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import secretariat.exception.BadInstanciationException;
import secretariat.exception.NotImplementedException;

public class Etudiant {

	private String codePermanent, nom, prenom;
	private int noProgramme, credits;
	private double moyenneCumul;

	private transient int nombreCours;

	// Représente la liste des inscriptions
	/**
	 * On doit pouvoir obtenir un {@link Iterator} qui permet de parcourir la
	 * liste des inscriptions de l'étudiant.
	 * Indice : vous pouvez créer une classe qui implémente l'interface Iterable
	 */
	private transient List<Inscription> inscriptions = new ArrayList<>();

	public Etudiant(String codePermanent, String nom, String prenom, int noProgramme, int credits) {
		super();
		this.codePermanent = codePermanent;
		this.nom = nom;
		this.prenom = prenom;
		this.noProgramme = noProgramme;
		this.credits = credits;
		this.moyenneCumul = 0;

		//valideEtat();
	}

	/**
	 * Valide la consistance des entrées. Le cas échéant, lance une
	 * {@link BadInstanciationException}.
	 * 
	 * <p>
	 * 
	 * TODO - À vous de l'implémenter ( <i>indice</i>: vous pouvez vous inspirer de
	 * celle présente dans {@link Cours})
	 */
	private void valideEtat() {

		throw new NotImplementedException();

	}

	public void addInscription(Inscription inscription) {
		inscriptions.add(inscription);
	}

	public void removeInscription(Cours cours) {
		inscriptions.removeIf(inscription -> inscription.getCours().getSigle() == cours.getSigle());
	}

	public double getMoyenneCumul() {
		return moyenneCumul;
	}

	public String getCodePermanent() {
		return codePermanent;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getNoProgramme() {
		return noProgramme;
	}

	public int getCredits() {
		return credits;
	}

	public int getNombreCours() {
		return nombreCours;
	}

	/**
	 * TODO Doit retourner un <b>nouvel</b> {@link Iterator} d'inscription
	 * permettant de parcourir la list des inscriptions.
	 * 
	 * @return
	 */
	public Iterator<Inscription> iterator() {
		return inscriptions.iterator();
	}

	public void setMoyenneCumul(double moyenneCumul) {
		this.moyenneCumul = moyenneCumul;
	}

	public void setNoProgramme(int noProgramme) {
		this.noProgramme = noProgramme;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

}
