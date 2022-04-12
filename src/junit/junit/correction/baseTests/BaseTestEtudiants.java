package junit.junit.correction.baseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import secretariat.Etudiant;
import secretariat.Inscription;

/**
 * 
 * @author Louis Hamel
 *
 */
class BaseTestEtudiants {

	private Etudiant[] etudiants;

	@BeforeEach
	void setUp() throws Exception {
		etudiants = new Etudiant[] { new Etudiant("POTJ19502563", "Potter", "James", 6033, 65),
				new Etudiant("MEUM19303265", "Meurseault", "Monsieur", 2390, 23) };

		etudiants[0].setMoyenneCumul(3.2);
		etudiants[1].setMoyenneCumul(1.5);

	}

	@AfterEach
	void tearDown() throws Exception {
		etudiants = null;
	}

	@Test
	void testConstructeur() {

		new Etudiant("POTJ19502563", "Potter", "James", 6033, 65);
		new Etudiant("MEUM19303265", "Meurseault", "Monsieur", 2390, 23);

	}

	@Test
	void testAccesseurs() {
		assertEquals("POTJ19502563", etudiants[0].getCodePermanent());
		assertEquals("MEUM19303265", etudiants[1].getCodePermanent());

		assertEquals("Potter", etudiants[0].getNom());
		assertEquals("Meurseault", etudiants[1].getNom());

		assertEquals("James", etudiants[0].getPrenom());
		assertEquals("Monsieur", etudiants[1].getPrenom());

		assertEquals(6033, etudiants[0].getNoProgramme());
		assertEquals(2390, etudiants[1].getNoProgramme());

		assertEquals(65, etudiants[0].getCredits());
		assertEquals(23, etudiants[1].getCredits());

		assertEquals(3.2, etudiants[0].getMoyenneCumul(), 0.0001);
		assertEquals(1.5, etudiants[1].getMoyenneCumul(), 0.0001);

	}

	@Test
	void testInitialIterator() {

		for (Etudiant etudiant : etudiants) {
			Iterator<Inscription> itInscr = etudiant.iterator();
			assertFalse(itInscr.hasNext());

			// Chaque invocation devrait retourner une nouvelle instance
			assertFalse(etudiant.iterator() == etudiant.iterator());
		}

	}

}
