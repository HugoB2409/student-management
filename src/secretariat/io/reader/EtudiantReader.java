package secretariat.io.reader;

import secretariat.Etudiant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class EtudiantReader implements Reader<Collection<Etudiant>> {
    private Scanner myReader;

    @Override
    public Collection<Etudiant> read(File file) {
        var etudiants = new ArrayList<Etudiant>();
        try {
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                try {
                    if (!data.equals("")) {
                        String[] values = data.split("\\t");
                        if(!values[0].startsWith("//")) {
                            etudiants.add(parse(values));
                        }

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    private Etudiant parse(String[] values) {
        return new Etudiant(values[0], values[1], values[2], Integer.parseInt(values[3]), Integer.parseInt(values[4]));
    }
}
