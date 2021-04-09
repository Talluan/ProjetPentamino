import java.util.*;
import java.io.*;

/**
 * Classe abstraite joueur
 */
public abstract class Joueur implements Serializable {
    /**
     * Attributs
     */
    /**
     * nom du joueur
     */
    private String nom;

    /**
     * score moyen du joueur
     */
    private double scoreMoyen;

    /**
     * liste des parties du joueur
     */
    private ArrayList<Partie> liste;

    /**
     * Constructeur du joueur
     * @param nom nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.scoreMoyen = 0;
        this.liste = new ArrayList<Partie>();
    }

    /**
     * méthode à définir permettant de poser une pièce
     * @param piece pièce à poser
     * @return un booléen indiquant si la pièce a été posée
     */
    public abstract boolean poserPiece(Piece piece);

    /**
     * méthode permettant d'afficher les parties du joueur
     */
    public void afficherParties() {
        for (int i = 0; i < this.liste.size(); i++) {
            System.out.print(i + " :");
            this.liste.get(i).afficherDetails();
        }
    }

    /**
     * méthode à définir dans les classes qui héritent, permet d'obtenir le score du joueur
     * @return
     */
    public abstract double calculerScore();

    /**
     * méthode permetant de calculer le score moyen du joueur
     */
    public void calculerScoreMoyen(){
        double total=0;
        for(Partie p : this.liste){
            total+=p.getScore();
        }
        this.scoreMoyen=total/this.liste.size();
    }

    /**
     * ajoute une partie à la liste de partie
     * @param p partie à ajouter
     */
    public void ajouterPartie(Partie p){
        this.liste.add(p);
    }

    /**
     * change le nom du joueur
     * @param name nom à donner
     */
    public void setNom(String name) {
        this.nom = name;
    }

    /**
     * méthode donnant le nom du joueur
     * @return le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * méthode changeant le score moyen du joueur
     * @param sc score à mettre
     */
    public void setScore(double sc) {
        this.scoreMoyen = sc;
    }

    /**
     * méthode qui retourne le score moyen du joueur
     * @return
     */
    public double getScore() {
        return this.scoreMoyen;
    }

    /**
     * méthode qui retourne la liste de parties du joueur
     * @return liste de parties
     */
    public ArrayList<Partie> getListe() {
        return this.liste;
    }
    
}