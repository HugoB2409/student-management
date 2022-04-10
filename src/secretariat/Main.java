package secretariat;

import secretariat.io.Util;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        Util util = new Util();
        File file = new File("data/Cours.txt");
        File file2 = new File("data/Etudiants.txt");
        File file3 = new File("data/Inscriptions.txt");
        var test = Util.getCoursReader().read(file);
        var test2 = Util.getEtudiantReader().read(file2);
        var test3 = util.getInscriptionReader(new TableauPrincipal()).read(file3);

        new MenuController().start();
    }
}
