import java.util.*;
import java.io.*;

public class Jeu{

    /**
     * Attributs
     */

    /**
     * liste de joueurs
     */
    public static ArrayList<Joueur> listeJoueur=new ArrayList<Joueur>();

    /**
     * Joueur courant
     */
    public static Joueur joueurCharge;

    /**
     * Partie courante
     */
    public static Partie game;

    /**
     * méthode qui permet de charger la liste de joueurs sauvegardée
     */
    public static void chargerListeJoueur(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("donnes.dat"));
            //on est obligé de faire cela car sinon le programme ne veut pas compiler alors qu'il est juste
            @SuppressWarnings("unchecked")
            ArrayList<Joueur> inter=(ArrayList<Joueur>) ois.readObject();
            Jeu.listeJoueur = new ArrayList<Joueur>(inter);
            ois.close();
            for(Joueur j : Jeu.listeJoueur){
                j.calculerScoreMoyen();
            }
        }catch (IOException ioe){
            System.out.println("ALERTE : Une erreur dans le chargement (erreur fichier)");
        }catch (ClassNotFoundException cnf){
            System.out.println("ALERTE : Une erreur dans le chargement (erreur classe)");
        }
    }

    /**
     * méthode qui permet de sauvegarder le joueur courant dans la liste des joueurs
     */
    public static void sauvegarder(){
        Jeu.joueurCharge.calculerScore();
        Jeu.joueurCharge.ajouterPartie(Jeu.game);
        Joueur joueurASupprimer = new Debutant("jarod");
        for(Joueur j : Jeu.listeJoueur){
            if(Jeu.joueurCharge.getNom().equals(j.getNom())){
                joueurASupprimer=j;
            }
        }
        Jeu.listeJoueur.remove(joueurASupprimer);
        Jeu.listeJoueur.add(Jeu.joueurCharge);

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("donnes.dat"));
            oos.writeObject(Jeu.listeJoueur);
            oos.close();
        }catch (IOException ioe){
            System.out.println("ALERTE : Une erreur dans la sauvegarde (erreur fichier)");
        }
    }

    /**
     * Comparateur par nom du joueur
     */
    public static Comparator<Joueur> compareByName = new Comparator<Joueur>() {
        @Override
        public int compare(Joueur j1, Joueur j2) {
            return j1.getNom().compareTo(j2.getNom());
        }
    };

    /**
     * Comparateur par score du joueur
     */
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


    /**
     * méthode qui permet d'afficher la liste de joueurs par ordre alphabétique
     */
    public static void afficherListeAlpha() {
        ArrayList<Joueur> listeAlpha = Jeu.listeJoueur;
        Collections.sort(listeAlpha, compareByName);
        for (int i = 0; i < listeAlpha.size(); i++) {
            System.out.printf("%d : %12s %7.2f\n"  , i, listeAlpha.get(i).getNom(), listeAlpha.get(i).getScore());
        }
    }

    /**
     * méthode qui permet d'afficher la liste de joueurs triée par score
     */
    public static void afficherListeScore() {
        ArrayList<Joueur> listeScore = Jeu.listeJoueur;
        Collections.sort(listeScore, compareByScore);
        for (int i = 0; i < listeScore.size(); i++) {
            System.out.printf("%d : %12s %7.2f\n"  , i, listeScore.get(i).getNom(), listeScore.get(i).getScore());
        }
    }

    /**
     * méthode qui permet d'afficher le menu principal du jeu
     */
    public static int affichageMenu() {
        System.out.println("0 : quitter le jeu");
        System.out.println("1 : creer un nouveau joueur");
        System.out.println("2 : choisir un joueur");
        System.out.print("Que voulez vous faire ?  ");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        System.out.println();
        return choix;
    }

    /**
     * méthode qui permet de choisir un joueur parmi la liste existante
     */
    public static Joueur choisirJoueur() {
        // cas où la liste de joueurs est vide
        if(Jeu.listeJoueur.size()==0){
            System.out.println("Pas de joueur! Redirection vers la creation de joueur");
            Jeu.creerJoueur();
        }
        System.out.println("Quel joueur desirez vous jouer ?");

        System.out.println("Affichage alphabetique");
        Jeu.afficherListeAlpha();

        System.out.println("Affichage au score");
        Jeu.afficherListeScore();
        
        // réception du résultat
        Scanner sc = new Scanner(System.in);
        System.out.print("Votre choix : ");
        int choix = sc.nextInt();
        while (choix < 0 || choix > Jeu.listeJoueur.size()) {
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
        }
        System.out.println();
        return Jeu.listeJoueur.get(choix);
    }

    /**
     * méthode qui permet de créer un nouveau joueur
     */
    public static void creerJoueur(){
        Scanner sc = new Scanner(System.in);
        int difficulte;
        String nom,validation;
        boolean existeDeja=false;
        boolean termine=false;
        System.out.println("Saisissez le nom du nouveau joueur : ");
        while(!termine){
            nom = sc.nextLine();
            for(Joueur j : Jeu.listeJoueur){
                if(nom.equals(j.getNom())){
                    System.out.println("Ce nom de joueur est deja pris!");
                    existeDeja=true;
                }
            }
            while(existeDeja){
                System.out.println("Entrez un nom non existant : ");
                nom = sc.nextLine();
                existeDeja=false;
                for(Joueur j : Jeu.listeJoueur){
                    if(nom.equals(j.getNom())){
                        System.out.println("Ce nom de joueur est deja pris!");
                        existeDeja=true;
                    }
                }
            }
            System.out.println("etes vous sur de bien creer un joueur nomme \""+nom+"\"? (oui ou non)");
            validation = sc.nextLine();
            if(validation.equals("oui")){
                System.out.println("Choisissez une difficulte (1 : Debutant, 2 : Intermediaire, 3 : Avance) :");
                difficulte = sc.nextInt();
                while(difficulte<1 && difficulte>3){
                    System.out.println("Mauvais chiffre! (tapez un nombre entre 1 et 3) :");
                    difficulte = sc.nextInt();
                }
                switch(difficulte){
                    case 1:
                        Jeu.listeJoueur.add(new Debutant(nom));
                        break;
                    case 2:
                        Jeu.listeJoueur.add(new Intermediaire(nom));
                        break;
                    case 3:
                        Jeu.listeJoueur.add(new Avance(nom));
                        break;
                    default:
                        Jeu.listeJoueur.add(new Debutant(nom));
                        break;
                }
                System.out.println("Joueur cree avec succes!");
                termine=true;
            }else{
                System.out.println("Saisissez a nouveau un nom : ");
            }
        }
    }

    /**
     * méthode qui permet de choisir une partie dans la liste du joueur chargé
     */
    public static void choisirPartie() {
        if(Jeu.joueurCharge.getListe().size()==0){
            System.out.println("Pas de partie! Redirection vers la creation de partie");
            Jeu.creerPartie();
        }
        Jeu.joueurCharge.afficherParties();
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        while (choix < 0 || choix > Jeu.joueurCharge.getListe().size()) {
            choix = sc.nextInt();
        }
        Jeu.game = (Partie)Jeu.joueurCharge.getListe().get(choix);
        Jeu.joueurCharge.getListe().remove(choix);
    }

    /**
     * méthode qui permet de créer une nouvelle partie
     */
    public static void creerPartie() {
        // On ajoute une partie à la liste de parties du joueur
        Jeu.joueurCharge.getListe().add(new Partie());
        // On place la partie créée dans la partie courante pour la jouer
        Jeu.game =(Partie) Jeu.joueurCharge.getListe().get(Jeu.joueurCharge.getListe().size()-1);
    }

    /**
     * méthode qui permet de jouer une partie
     */
    public static void startGame() {
        if (Jeu.joueurCharge != null && Jeu.game != null) {
            boolean sortie = false;
            while (!sortie) {
                Jeu.game.afficherGrille();
                System.out.println();
                Jeu.game.afficherListePiece();
                System.out.println("Quelle piece voulez vous jouer ? Tapez -1 pour quitter la partie.");
                Scanner sc = new Scanner(System.in);
                System.out.print("Votre choix : ");
                int choix = sc.nextInt();
                System.out.println();

                Piece p = null;

                // On sort de la partie si l'utilisateur tape -1
                while (choix < -1 || choix > Jeu.game.getPiaPosees().size()) {
                    System.out.println("Tapez un numero existant ! -1 pour quitter.");
                }
                if (choix == -1) {
                    sortie = true;
                }
                else {
                    p = Jeu.game.getPiaPosees().get(choix);
                    Jeu.joueurCharge.poserPiece(p);
                }
            }
        }
        return;
    }

    /**
     * méthode qui permet l'affichage du menu des parties
     * @return un entier correspondant au choix du joueur
     */
    public static int menuParties() {
        System.out.println("0 : retourner au menu");
        System.out.println("1 : creer partie");
        System.out.println("2 : choisir partie");
        Scanner sc = new Scanner(System.in);
        System.out.print("Votre choix : ");
        int choix = sc.nextInt();
        while (choix < 0 || choix > 2) {
            System.out.print("Votre choix : ");
            choix = sc.nextInt();
        }
        System.out.println();
        return choix;
    }

    /**
     * méthode principale du jeu, permet les différents affichages, et le chargement des fichiers de sauvegarde
     * @param args
     */
    public static void main(String[] args){
        boolean session = true;
        chargerListeJoueur();
        System.out.println();
        System.out.println("    ____             __                  _           \n   / __ \\___  ____  / /_____ _____ ___  (_)___  ____ \n  / /_/ / _ \\/ __ \\/ __/ __ `/ __ `__ \\/ / __ \\/ __ \\\n / ____/  __/ / / / /_/ /_/ / / / / / / / / / / /_/ /\n/_/    \\___/_/ /_/\\__/\\__,_/_/ /_/ /_/_/_/ /_/\\____/ ");
        System.out.println();
        while (session) {
            // affiche l'accueil
            int choix = Jeu.affichageMenu();

            // cas de la création d'un joueur
            if (choix == 1) {
                Jeu.creerJoueur();
            }

            // cas de la sélection d'un joueur
            else if (choix == 2) {
                // Initialise le joueur dans le joueur courant
                Jeu.joueurCharge = Jeu.choisirJoueur();
                // Initialise la partie dans la partie courante
                int dec = Jeu.menuParties();
                if (dec == 0)
                    break;
                else if (dec == 1)
                    Jeu.creerPartie();
                else if (dec == 2)
                    Jeu.choisirPartie();

                Jeu.startGame();
            } 

            // cas de sortie du jeu
            else if (choix == 0) {
                session = false;
            }
        }

        Jeu.sauvegarder();
        System.exit(0);

    }
}