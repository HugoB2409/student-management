package secretariat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import secretariat.exception.BadInstanciationException;
import secretariat.exception.NotImplementedException;

/**
 * Hugo Bélanger
 * Maxime Desmarais
 *
 * Classe représentant un etudiant
 */
public class Etudiant {

    private final String codePermanent;
    private final String nom;
    private final String prenom;
    private int noProgramme, credits;
    private double moyenneCumul;

    private transient int nombreCours;

    // Représente la liste des inscriptions
    /**
     * On doit pouvoir obtenir un {@link Iterator} qui permet de parcourir la
     * liste des inscriptions de l'étudiant.
     * Indice : vous pouvez créer une classe qui implémente l'interface Iterable
     */
    private final transient List<Inscription> inscriptions = new ArrayList<>();

    public Etudiant(String codePermanent, String nom, String prenom, int noProgramme, int credits) {
        super();
        this.codePermanent = codePermanent;
        this.nom = nom;
        this.prenom = prenom;
        this.noProgramme = noProgramme;
        this.credits = credits;
        this.moyenneCumul = 0;

        valideEtat();
    }

    /**
     * Valide la consistance des entrées. Le cas échéant, lance une
     * {@link BadInstanciationException}.
     *
     * <p>
     * <p>
     * TODO - À vous de l'implémenter ( <i>indice</i>: vous pouvez vous inspirer de
     * celle présente dans {@link Cours})
     */
    private void valideEtat() {
        if (codePermanent == null)
            throw new BadInstanciationException("Code permanent nul");
        if (prenom == null)
            throw new BadInstanciationException("Nom nul");
        if (nom == null)
            throw new BadInstanciationException("Nom nul");

    }

    public void addInscription(Inscription inscription) {
        if (!inscriptions.isEmpty()) {
            inscriptions.get(inscriptions.size() - 1).setProchainCours(inscription);
        }
        inscriptions.add(inscription);
    }

    public boolean removeInscription(Cours cours) {
        return inscriptions.removeIf(inscription -> inscription.getCours().getSigle().equals(cours.getSigle()));
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
