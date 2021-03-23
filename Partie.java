import java.io.Serializable;
import java.util.ArrayList;

public class Partie implements Serializable{
    
    /* Attributs */

    /**
     * Hauteur de la grille
     */
    public final static int hauteur;

    /**
     * Largeur de la grille
     */
    public final static int largeur;


    /**
     * Tableau à 2 dimensions représentant 
     */
    private char grille[][];

    /**
     * Liste des pièces posées
     */
    private ArrayList<Piece> piPosees;

    /**
     * Liste des pièces à poser
     */
    private ArrayList<Piece> piaPosees;



    /**
     * Constructeur,  A FINIR !!!!!!
     */
    public Partie() {
        this.grille = new char [Partie.largeur][Partie.largeur];
        int i = 0;


    }

}
