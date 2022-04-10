package secretariat;

import secretariat.io.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int exit = 10;

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
        } while (userChoice != exit);

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
        System.out.println("8: Ajouter un étudiant");
        System.out.println("9: Ajouter un cours");
        System.out.println("10: Quitter");
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

            if (choice <= 0 || choice > exit) {
                System.out.println("Choix invalide");
            }
        } while (choice <= 0 || choice > exit);
        // Flush scanner
        scanner.nextLine();
        return choice;
    }

    private static void executeOperation(int choice, TableauPrincipal tp) {
        switch (choice) {
            case 1 -> tp.initFromFiles();
            case 2 -> tp.saveModifications();
            case 3 -> tp.inscrire(getCourseCode(), getStudentCode());
            case 4 -> tp.desinscrire(getCourseCode(), getStudentCode());
            case 5 -> modifyInscription(tp);
            case 6 -> tp.getEtudiant(getStudentCode());
            case 7 -> tp.getCour(getCourseCode());
            case 8 -> tp.ajouterEtudiant(readStudentInfo());
            case 9 -> tp.ajouterCours(readCourseInfo(tp));
        }
    }

    private static Etudiant readStudentInfo() {
        System.out.print("Entrez le code permanent: ");
        var codePermanent = scanner.nextLine();
        System.out.print("Entrez le nom: ");
        var nom = scanner.nextLine();
        System.out.print("Entrez le prenom: ");
        var prenom = scanner.nextLine();
        System.out.print("Entrez le numero de programme: ");
        var noProgramme = scanner.nextInt();
        System.out.print("Entrez le nombre de crédits: ");
        var credits = scanner.nextInt();

        return new Etudiant(codePermanent, nom, prenom, noProgramme, credits);
    }

    private static Cours readCourseInfo(TableauPrincipal tp) {
        System.out.print("Entrez le sigle: ");
        var sigle = scanner.nextLine();
        System.out.print("Entrez le nom: ");
        var nom = scanner.nextLine();
        System.out.print("Entrez le nombre maximum d'étudiant: ");
        var max = scanner.nextInt();

        // flush scanner
        scanner.nextLine();

        var preqs = new ArrayList<Cours>();
        String preq;
        do {
            System.out.print("Entrez le sigle des cours prérequis: ");
            preq = scanner.nextLine();
            preqs.add(tp.getCour(preq));
        } while(!preq.equals(""));

        return new Cours(sigle, nom, max, preqs);
    }

    private static void modifyInscription(TableauPrincipal tp) {
        System.out.println("Entrez les informations concernant le cours à desinscrire");
        tp.desinscrire(getCourseCode(), getStudentCode());
        System.out.println("Entrez les informations concernant le cours à inscrire");
        tp.inscrire(getCourseCode(), getStudentCode());
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
