package secretariat.io.reader;

import secretariat.Cours;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Hugo Bélanger
 * Maxime Desmarais
 *
 * Classe utilisé pour lire dans le fichier  Cours.txt
 */
public class CoursReader implements Reader<Collection<Cours>> {
    ArrayList<Cours> cours = new ArrayList<Cours>();

    @Override
    public Collection<Cours> read(File file) {
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                try {
                    if (!data.equals("")) {
                        String[] values = data.split("\\t");
                        if (!values[0].startsWith("//")) {
                            cours.add(parse(values));
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cours;
    }

    private Cours parse(String[] values) {
        ArrayList<Cours> prerequis = new ArrayList<>();
        if (values.length == 4) {
            for (String sigle : values[3].split(" ; ")) {
                prerequis.add(cours.stream().filter(cour -> cour.getSigle().equals(sigle)).toList().get(0));
            }
        }

        return new Cours(values[0], values[1], Integer.parseInt(values[2]), prerequis);
    }
}
