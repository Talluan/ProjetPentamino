import java.util.*;
import java.io.*;

public abstract class Joueur implements Serializable {
    private String nom;
    private double scoreMoyen;
    private ArrayList<Partie> liste;

    public Joueur(String nom) {
        this.nom = nom;
        this.scoreMoyen = 0;
        this.liste = new ArrayList<Partie>();
    }

    public boolean poserPiece(Piece piece) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ou voulez vous placer la piece ?");
        System.out.print("En X ? : ");
        int x = sc.nextInt();
        System.out.print("En Y ? : ");
        int y = sc.nextInt();
        try {
            Jeu.game.ajouterPiece(piece, x, y);
        } catch (CaseDejaRemplieException e) { // le type de l'exeption va changer là c'est si les pièces se superposent
            Jeu.game.retirerDernierePiece();
            System.out.println("Les pieces se superposent !"); 
            return false;
        } catch (PieceDebordeException e) {
            Jeu.game.retirerDernierePiece();
            System.out.println("La piece sort de la grille !");
            return false;
        }
        return true;
        
    }

    public void afficherParties() {
        for (int i = 0; i < this.liste.size(); i++) {
            System.out.print(i + " :");
            this.liste.get(i).afficherDetails();
        }
    }


    public abstract double calculerScore();

    public void calculerScoreMoyen(){
        double total=0;
        for(Partie p : this.liste){
            total+=p.getScore();
        }
        this.scoreMoyen=total/this.liste.size();
    }

    public void ajouterPartie(Partie p){
        this.liste.add(p);
    }

    public void setNom(String name) {
        this.nom = name;
    }

    public String getNom() {
        return this.nom;
    }

    public void setScore(double sc) {
        this.scoreMoyen = sc;
    }

    public double getScore() {
        return this.scoreMoyen;
    }

    public ArrayList<Partie> getListe() {
        return this.liste;
    }
    
}