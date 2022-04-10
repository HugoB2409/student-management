package secretariat;

import secretariat.io.Util;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        new MenuController().start();

        File file = new File("data/Etudiants.txt");
//        var test = Util.getEtudiantReader().read(file);
//
//        for (Etudiant etudiant : test) {
//            System.out.println(etudiant.getCodePermanent());
//        }
    }
}
