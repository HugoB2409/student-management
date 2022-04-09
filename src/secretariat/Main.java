package secretariat;

import secretariat.io.Util;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
	    TableauPrincipal tp = new TableauPrincipal();
        File file = new File("data/Etudiants.txt");
//        var test = Util.getEtudiantReader().read(file);
//
//        for (Etudiant etudiant : test) {
//            System.out.println(etudiant.getCodePermanent());
//        }

        int userChoice;
        do {
            printMenu();
            userChoice = getUserChoice();
            executeOperation(userChoice, tp);
        } while (userChoice != 8);

    }

    private static void printMenu() {
        System.out.println("-----------------------------------");
        System.out.println("1: Initialiser les informations à partir des fichiers");
        System.out.println("2: Sauvegarder modifications");
        System.out.println("3: Inscrire un étudiant à un cours");
        System.out.println("4: Annuler l'inscription d'un étudiant à un cours");
        System.out.println("5: Modifier l'inscription d'un étudiant");
        System.out.println("6: Obtenir liste des cours suivis par un étudiant");
        System.out.println("7: Obtenir liste des étudiants inscrits à un cours");
        System.out.println("8: Quitter");
        System.out.println("-----------------------------------");
        System.out.print("Choix: ");
    }

    private static int getUserChoice() {
        int choice;
        do {
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                choice = -1;
                scanner.nextLine();
            }

            if (choice <= 0 || choice > 8) {
                System.out.println("Choix invalide");
            }
        } while (choice <= 0 || choice > 8);
        // Flush scanner
        scanner.nextLine();
        return choice;
    }

    private static void executeOperation(int choice, TableauPrincipal tp) {
        switch (choice) {
            case 1 -> tp.initFromFiles();
            case 2 -> tp.saveModifications();
            case 3 -> tp.inscrire(getCourseCode(), getStudentCode());
        }
    }

    private static String getCourseCode() {
        System.out.print("Entrez le sigle du cours: ");
        return scanner.nextLine();
    }

    private static String getStudentCode() {
        System.out.print("Entrez le code permanent de l'étudiant: ");
        return scanner.nextLine();
    }
}
