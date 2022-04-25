package secretariat.io.writer;

import secretariat.Etudiant;

import java.io.File;
import java.io.FileWriter;

/**
 * Hugo Bélanger
 * Maxime Desmarais
 *
 * Classe utilisé pour écrire dans le fichier  Etudiants.txt
 */
public class EtudiantWriter implements Writer<Iterable<Etudiant>> {
    @Override
    public void write(Iterable<Etudiant> obj, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Etudiant etudiant : obj) {
                var f = String.format("%s\t%s\t%s\t%d\t%d\t%.1f\n", etudiant.getCodePermanent(), etudiant.getNom(), etudiant.getPrenom(), etudiant.getNoProgramme(), 											etudiant.getCredits(), etudiant.getMoyenneCumul());
                fw.write(f);
                fw.flush();
            }
            fw.close();
        } catch (Exception ignored) {
        }
    }
}
