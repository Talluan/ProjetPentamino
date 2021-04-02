import java.util.*;
import java.io.*;

public class Jeu{


    public static ArrayList<Joueur> listeJoueur=new ArrayList<Joueur>();
    public static Joueur joueurCharge;
    public static int numeroPartie;

    public static void chargerListeJoueur(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("donnes.dat"));
            //on est obligé de faire cela car sinon le programme ne veut pas compiler alors qu'il est juste
            @SuppressWarnings("unchecked")
            ArrayList<Joueur> inter=(ArrayList<Joueur>) ois.readObject();
            Jeu.listeJoueur = new ArrayList<Joueur>(inter);
            ois.close();
        }catch (IOException ioe){
            System.out.println("ALERTE : Une erreur dans le chargement (erreur fichier)");
        }catch (ClassNotFoundException cnf){
            System.out.println("ALERTE : Une erreur dans le chargement (erreur classe)");
        }
    }

    public static void sauvegarder(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("donnes.dat"));
            oos.writeObject(Jeu.listeJoueur);
            oos.close();
        }catch (IOException ioe){
            System.out.println("ALERTE : Une erreur dans la sauvegarde (erreur fichier)");
        }
    }

    public static Comparator<Joueur> compareByName = new Comparator<Joueur>() {
        @Override
        public int compare(Joueur j1, Joueur j2) {
            return j1.getNom().compareTo(j2.getNom());
        }
    };

    public static Comparator<Joueur> compareByScore = new Comparator<Joueur>() {
        @Override
        public int compare(Joueur j1, Joueur j2) {
            if (j1.getScore() < j2.getScore()) {
                return -1;
            } 
            else if(j1.getScore() == j2.getScore()) {
                return j1.getNom().compareTo(j2.getNom());
            }
            else {
                return 1;
            }
        }
    };


    public static void afficherListeAlpha() {
        ArrayList<Joueur> listeAlpha = Jeu.listeJoueur;
        Collections.sort(listeAlpha, compareByName);
        for (int i = 0; i < listeAlpha.size(); i++) {
            System.out.printf("%d : %12s %7.2f\n"  , i, listeAlpha.get(i).getNom(), listeAlpha.get(i).getScore());
        }
    }

    public static void afficherListeScore() {
        ArrayList<Joueur> listeAlpha = Jeu.listeJoueur;
        Collections.sort(listeAlpha, compareByScore);
        for (int i = 0; i < listeAlpha.size(); i++) {
            System.out.printf("%d : %12s %7.2f\n"  , i, listeAlpha.get(i).getNom(), listeAlpha.get(i).getScore());
        }
    }

    public static int affichageMenu() {
        System.out.println("0 : quitter le jeu");
        System.out.println("1 : créer un nouveau joueur");
        System.out.println("2 : choisir un joueur");
        System.out.println("Que voulez vous faire ?");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        return choix;
    }

    public static int choisirJoueur() {
        System.out.println("Quel joueur désirez vous jouer ?");
        System.out.println("Affichage alphabetique");
        Jeu.afficherListeAlpha();

        System.out.println("Affichage au score");
        Jeu.afficherListeScore();
        
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        while (choix < 0 || choix > Jeu.listeJoueur.size()) {
            choix = sc.nextInt();
        }
        return choix;
    }



    public static int choisirPartie() {
        Jeu.joueurCharge.afficherParties();
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        while (choix < 0 || choix > Jeu.listeJoueur.size()) {
            choix = sc.nextInt();
        }
        return choix;
    }

    public static void main(String[] args){
        boolean session = true;
        while (session) {
            int choix = Jeu.affichageMenu();
            if (choix == 1) {
                System.out.println("Affaire à suivre...");

            }
            else if (choix == 2) {
                Jeu.joueurCharge = Jeu.listeJoueur.get(Jeu.choisirJoueur());
            } 
        }

        System.exit(0);

    }
}