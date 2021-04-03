import java.util.*;
import java.io.*;

public class Jeu{


    public static ArrayList<Joueur> listeJoueur=new ArrayList<Joueur>();
    public static Joueur joueurCharge;
    public static Partie game;

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
        Jeu.joueurCharge.calculerScore();
        Jeu.joueurCharge.ajouterPartie(Jeu.game);
        Joueur joueurASupprimer;
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

    public static Joueur choisirJoueur() {
        System.out.println("Quel joueur desirez vous jouer ?");
        System.out.println("Affichage alphabetique");
        Jeu.afficherListeAlpha();

        System.out.println("Affichage au score");
        Jeu.afficherListeScore();
        
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        while (choix < 0 || choix > Jeu.listeJoueur.size()) {
            choix = sc.nextInt();
        }
        return Jeu.listeJoueur.get(choix);
    }

    public static void creerJoueur(){
        Scanner sc = new Scanner(System.in);
        int difficulte;
        String nom,validation;
        boolean termine=false;
        System.out.println("Saisissez le nom du nouveau joueur : ");
        while(!termine){
            nom = sc.nextLine();
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

    public static void choisirPartie() {
        Jeu.joueurCharge.afficherParties();
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        while (choix < 0 || choix > Jeu.joueurCharge.getListe().size()) {
            choix = sc.nextInt();
        }
        Jeu.game = (Partie)Jeu.joueurCharge.getListe().get(choix);
        Jeu.joueurCharge.getListe().remove(choix);
    }

    public static void creerPartie() {
        // On ajoute une partie à la liste de parties du joueur
        Jeu.joueurCharge.getListe().add(new Partie());
        // On place la partie créée dans la partie courante pour la jouer
        Jeu.game =(Partie) Jeu.joueurCharge.getListe().get(Jeu.joueurCharge.getListe().size()-1);
    }

    public static void startGame() {
        if (Jeu.joueurCharge != null && Jeu.game != null) {
            boolean sortie = false;
            while (!sortie) {
                Jeu.game.afficherGrille();
                System.out.println();
                Jeu.game.afficherListePiece();
                System.out.println("Quelle piece voulez vous jouer ? Tapez -1 pour quitter la partie.");
                Scanner sc = new Scanner(System.in);
                int choix = sc.nextInt();

                Piece p = null;

                // On sort de la partie si l'utilisateur tape -1
                while (choix < -1 || choix > Jeu.game.getPiaPosees().size()) {
                    System.out.println("Tapez un numéro existant ! -1 pour quitter.");
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


    public static void main(String[] args){
        boolean session = true;
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