package junit.junit.correction.testsAvances;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import junit.junit.correction.baseTests.BaseTestInscriptions;
import secretariat.Cours;
import secretariat.Etudiant;
import secretariat.Inscription;
import secretariat.TableauPrincipal;

public class TestInscriptionsChaines extends BaseTestInscriptions {

    /**
     * Vérifie que les itérateurs suivent toujours le chaînage.
     *
     * @author Louis Hamel
     */
    public static void assertChainedInscription(TableauPrincipal tab) {

        for (Etudiant std : tab.getEtudiants()) {
            Iterator<Inscription> iter = std.iterator();

            Inscription previous = null, current = null;
            while (iter.hasNext()) {
                previous = current;
                current = iter.next();

                if (previous == null)
                    continue;

                //On vérifie que la prochaine inscription de cours de l'étudiant correspond au prochain dans la chaîne
                assertTrue(previous.getProchainCours() == current);
            }

            if (previous != null)// Une autre fois pour le dernier
                assertTrue(previous.getProchainCours() == current);

        }

        for (Cours crs : tab.getCours()) {
            Iterator<Inscription> iter = crs.iterator();

            Inscription previous = null, current = null;
            while (iter.hasNext()) {
                previous = current;
                current = iter.next();

                if (previous == null)
                    continue;

                //On vérifie que la prochaine inscription d'étudiant de ce cours correspond au prochain dans la chaîne
                assertTrue(previous.getProchainEtudiant() == current);
            }

            if (previous != null)// Une autre fois pour le dernier
                assertTrue(previous.getProchainEtudiant() == current);

        }

    }


    /**
     * On ajoute la condition de chainage aux tests, qui seront refaits tels quels.
     */
    @Override
    protected void assertCoherence(TableauPrincipal tableau) {
        super.assertCoherence(tableau);

        assertChainedInscription(tableau);
    }

}
