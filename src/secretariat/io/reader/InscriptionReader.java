package secretariat.io.reader;

import secretariat.Inscription;
import secretariat.TableauPrincipal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class InscriptionReader implements Reader<Collection<Inscription>> {
    private final TableauPrincipal tableau;

    public InscriptionReader(TableauPrincipal tableau) {
        this.tableau = tableau;
    }

    @Override
    public Collection<Inscription> read(File file) {
        var inscriptions = new ArrayList<Inscription>();
        Inscription prev = null;
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                try {
                    if (!data.equals("")) {
                        String[] values = data.split("\\t");
                        if(!values[0].startsWith("//")) {
                            Inscription inscription = parse(values);
                            inscriptions.add(inscription);
                            tableau.getEtudiant(inscription.getEtudiant().getCodePermanent()).addInscription(inscription);
                            tableau.getCour(inscription.getCours().getSigle()).addInscription(inscription);
                            if (prev != null) {
                                prev.setProchainCours(inscription);
                                prev.setProchainEtudiant(inscription);
                            }
                            prev = inscription;
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inscriptions;
    }

    private Inscription parse(String[] values) {
        return new Inscription(tableau.getCour(values[0]), tableau.getEtudiant(values[1]));
    }
}
