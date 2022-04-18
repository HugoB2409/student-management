package junit.junit.correction.testsAvances;

import static junit.junit.correction.baseTests.BaseTestInscriptions.*;
import static junit.junit.util.DataMaker.makeDefaultTabEtudCours;
import static junit.junit.util.DataMaker.makeDefaultTableau;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import secretariat.Cours;
import secretariat.Etudiant;
import secretariat.Inscription;
import secretariat.TableauPrincipal;

/**
 * On va ici tester que le tableau n'accepte pas les doublons, ni pour les
 * cours, ni les étudiants, ni les inscriptions. On va aussi tester le retrait
 * d'une inscription inexistante
 *
 * @author Louis Hamel
 */
class TestAjoutDouble {

    private TableauPrincipal tableau;

    @BeforeEach
    void setUp() throws Exception {
        tableau = makeDefaultTabEtudCours();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testAjouterEtudiant() {

        Iterable<Etudiant> etudiants = tableau.getEtudiants();

        for (Etudiant etudiant : etudiants) {

            try {
                tableau.ajouterEtudiant(etudiant);

            } catch (Exception e) {

            }

            try {
                Etudiant bidon = new Etudiant(etudiant.getCodePermanent(), "bidon", "bidon", 1, 1);
                tableau.ajouterEtudiant(bidon);

            } catch (Exception e) {

            }

            int occurences = 0;

            for (Etudiant std : tableau.getEtudiants()) {
                if (std.getCodePermanent().equals(etudiant.getCodePermanent()))
                    occurences++;
            }
            assertTrue(occurences < 2, "CP = " + etudiant.getCodePermanent() + " présent " + occurences + " fois.");

        }

    }

    @Test
    void testAjouterCours() {

        Iterable<Cours> lesCours = tableau.getCours();

        for (Cours cours : lesCours) {

            try {
                tableau.ajouterCours(cours);

            } catch (Exception e) {

            }

            try {
                Cours bidon = new Cours(cours.getSigle(), "bidon", 1, new ArrayList<Cours>());
                tableau.ajouterCours(bidon);

            } catch (Exception e) {

            }

            int occurences = 0;

            for (Cours crs : tableau.getCours()) {
                if (crs.getSigle().equals(cours.getSigle()))
                    occurences++;
            }
            assertTrue(occurences < 2, "Sigle = " + cours.getSigle() + " présent " + occurences + " fois.");

        }

    }

    @Test
    void testInscrireCoursEtudiant() {

        tableau = makeDefaultTableau(false);

        assertCoherence(tableau);

        for (Inscription inscript : collectInscriptions(tableau)) {

            boolean inscrit = tableau.inscrire(inscript.getCours(), inscript.getEtudiant());

            assertCoherence(tableau);

            int occurences = 0;
            // On compte le nombre d'inscriptions identiques à inscript

            for (Inscription inscription : collectInscriptions(tableau)) {

                boolean equals = inscript.getCours().getSigle().equals(inscription.getCours().getSigle());
                equals &= inscript.getEtudiant().getCodePermanent()
                        .equals(inscription.getEtudiant().getCodePermanent());
                if (equals)
                    occurences++;
            }

            assertEquals(1, occurences);

            assertFalse(inscrit, "TableauPrincipal.inscrire(Cours, Etudiant) a retourné vrai sur un doublon");

        }

        assertCoherence(tableau);
    }

    /**
     * On va essayer d'inscrire un cours et un étudiant absents de la liste
     */
    @Test
    void testInscrireHack() {

        tableau = makeDefaultTableau(false);

        Cours crs;
        Etudiant std;
        crs = getElem(tableau.getCours()::iterator, 1);

        std = getElem(tableau.getEtudiants()::iterator, 1);

        Cours coursHack = new Cours(crs.getSigle(), "Bidon", 1, new ArrayList<Cours>());
        Etudiant stdHack = new Etudiant(std.getCodePermanent(), "Bidon", "Bidon", 1, 1);

        boolean aInscrit = tableau.inscrire(crs, stdHack);

        assertCoherence(tableau);

        assertFalse(aInscrit);

        aInscrit = tableau.inscrire(coursHack, std);

        assertCoherence(tableau);

        assertFalse(aInscrit);

        boolean desinscrire = tableau.desinscrire(coursHack, stdHack);

        assertCoherence(tableau);

        assertFalse(desinscrire);
    }

    public static <T> T getElem(Iterable<T> elem, int index) {
        int i = 0;

        for (T t : elem) {
            if (i++ == index)
                return t;
        }

        return null;

    }

    public static void assertCoherence(TableauPrincipal tab) {
        assertInscriptionsInBothLists(tab);
        assertRelatedInscriptions(tab);
    }

    @Test
    void testInscrireStringString() {

        tableau = makeDefaultTableau(false);

        assertCoherence(tableau);

        for (Inscription inscript : collectInscriptions(tableau)) {

            boolean inscrit = tableau.inscrire(inscript.getCours().getSigle(),
                    inscript.getEtudiant().getCodePermanent());

            assertCoherence(tableau);

            int occurences = 0;
            // On compte le nombre d'inscriptions identiques à inscript

            for (Inscription inscription : collectInscriptions(tableau)) {

                boolean equals = inscript.getCours().getSigle().equals(inscription.getCours().getSigle());
                equals &= inscript.getEtudiant().getCodePermanent()
                        .equals(inscription.getEtudiant().getCodePermanent());
                if (equals)
                    occurences++;
            }

            assertEquals(1, occurences);

            assertFalse(inscrit, "TableauPrincipal.inscrire(Cours, Etudiant) a retourné vrai sur un doublon");

        }

        assertCoherence(tableau);

    }

    @Test
    void testDesinscrireCoursEtudiant() {

        Cours crs;
        Etudiant std;
        crs = tableau.getCours().iterator().next();
        std = tableau.getEtudiants().iterator().next();

        boolean estDesinscrit = tableau.desinscrire(crs, std);

        assertCoherence(tableau);

        assertFalse(estDesinscrit);

    }

    @Test
    void testDesinscrireStringString() {

        Cours crs;
        Etudiant std;
        crs = tableau.getCours().iterator().next();
        std = tableau.getEtudiants().iterator().next();

        boolean estDesinscrit = tableau.desinscrire(crs.getSigle(), std.getCodePermanent());

        assertCoherence(tableau);

        assertFalse(estDesinscrit);

    }

    @Test
    void testDesinscrireAbsent() {

        Cours crs;
        Etudiant std;
        crs = new Cours("BID0666", "Bidon absent", 5, new ArrayList<>());
        std = new Etudiant("BIDO48855885", "Bidon", "Absent", 2553, 2);

        boolean estDesinscrit = tableau.desinscrire(crs.getSigle(), std.getCodePermanent());

        assertCoherence(tableau);

        assertFalse(estDesinscrit);

        estDesinscrit = tableau.desinscrire(crs, std);

        assertCoherence(tableau);

        assertFalse(estDesinscrit);

    }

}
