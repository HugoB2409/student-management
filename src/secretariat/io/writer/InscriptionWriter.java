package secretariat.io.writer;

import secretariat.Inscription;

import java.io.File;
import java.io.FileWriter;

public class InscriptionWriter implements Writer<Iterable<Inscription>> {
    @Override
    public void write(Iterable<Inscription> obj, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Inscription inscription : obj) {
                var f = String.format("%s\t%s\n", inscription.getCours().getSigle(), inscription.getEtudiant().getCodePermanent());
                fw.write(f);
                fw.flush();
            }
            fw.close();
        } catch (Exception ignored) {
        }
    }
}
