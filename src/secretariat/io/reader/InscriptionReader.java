package secretariat.io.reader;

import secretariat.Etudiant;
import secretariat.Inscription;
import secretariat.TableauPrincipal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class InscriptionReader implements Reader<Collection<Inscription>> {
    private Scanner myReader;
    private TableauPrincipal tableau;

    public InscriptionReader(TableauPrincipal tableau) {
        this.tableau = tableau;
    }

    @Override
    public Collection<Inscription> read(File file) {
        var inscriptions = new ArrayList<Inscription>();
        try {
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                try {
                    if (!data.equals("")) {
                        String[] values = data.split("\\t");
                        if(!values[0].startsWith("//")) {
                            inscriptions.add(parse(values));
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
