package secretariat;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hugo Bélanger
 * Maxime Desmarais
 *
 * Classe représentant l'ensemble des intéraction a la console
 */
public class MenuController {
    private final Scanner scanner = new Scanner(System.in);
    private final int exit = 10;
    private TableauPrincipal tp;

    public void start() {
        tp = new TableauPrincipal();

        int userChoice;
        do {
            printMenu();
            userChoice = getUserChoice();
            executeOperation(userChoice);
        } while (userChoice != exit);
    }

    private void printMenu() {
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

    private int getUserChoice() {
        int choice;
        do {
            choice = readInt();

            if (choice <= 0 || choice > exit) {
                System.out.println("Choix invalide");
            }
        } while (choice <= 0 || choice > exit);
        // flush scanner
        scanner.nextLine();
        return choice;
    }

    private void executeOperation(int choice) {
        switch (choice) {
            case 1 -> tp.initFromFiles();
            case 2 -> tp.saveModifications();
            case 3 -> inscription();
            case 4 -> tp.desinscrire(getCourseCode(), getStudentCode());
            case 5 -> modifyInscription();
            case 6 -> getCoursByStudent();
            case 7 -> getStudentByCours();
            case 8 -> tp.ajouterEtudiant(readStudentInfo());
            case 9 -> tp.ajouterCours(readCourseInfo());
        }
    }

    private void getCoursByStudent() {
        ArrayList<Cours> cours = (ArrayList<Cours>) tp.findCoursByStudent(getStudentCode());
        for (Cours cour : cours) {
            System.out.println(cour.getNom());
        }
    }

    private void getStudentByCours() {
        ArrayList<Etudiant> etudiants = (ArrayList<Etudiant>) tp.findStudentByCours(getCourseCode());
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant.getNom());
        }
    }

    private void inscription() {
        boolean inscription = tp.inscrire(getCourseCode(), getStudentCode());
        if (!inscription) {
            System.out.println("Une erreure est surrvenue lors de l'inscription");
        }
    }

    private Etudiant readStudentInfo() {
        System.out.print("Entrez le code permanent: ");
        var codePermanent = scanner.nextLine();
        System.out.print("Entrez le nom: ");
        var nom = scanner.nextLine();
        System.out.print("Entrez le prenom: ");
        var prenom = scanner.nextLine();
        System.out.print("Entrez le numero de programme: ");
        var noProgramme = readInt();
        System.out.print("Entrez le nombre de crédits: ");
        var credits = readInt();

        return new Etudiant(codePermanent, nom, prenom, noProgramme, credits);
    }

    private Cours readCourseInfo() {
        System.out.print("Entrez le sigle: ");
        var sigle = scanner.nextLine();
        System.out.print("Entrez le nom: ");
        var nom = scanner.nextLine();
        System.out.print("Entrez le nombre maximum d'étudiant: ");
        var max = readInt();

        // flush scanner
        scanner.nextLine();

        var preqs = new ArrayList<Cours>();
        String preq;
        do {
            System.out.print("Entrez le sigle des cours prérequis: ");
            preq = scanner.nextLine();
            Cours cour = tp.getCour(preq);
            if (cour == null) {
                System.out.println("Il existe aucun cours comportant ce sigle.");
            } else {
                preqs.add(cour);
            }
        } while (!preq.equals(""));

        return new Cours(sigle, nom, max, preqs);
    }

    private void modifyInscription() {
        var studentCode = getStudentCode();
        System.out.println("Entrez les information du cours à désinscrire");
        tp.desinscrire(getCourseCode(), studentCode);
        System.out.println("Entrez les informations du cours à inscrire");
        tp.inscrire(getCourseCode(), studentCode);
    }

    private String getCourseCode() {
        System.out.print("Entrez le sigle du cours: ");
        return scanner.nextLine();
    }

    private String getStudentCode() {
        System.out.print("Entrez le code permanent de l'étudiant: ");
        return scanner.nextLine();
    }

    private int readInt() {
        Integer num = null;
        while (num == null) {
            try {
                num = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Format invalide veillez entrer un chiffre entier: ");
                // flush scanner
                scanner.nextLine();
            }
        }

        return num;
    }
}
