import java.io.Serializable;
import java.util.ArrayList;
import java.math.*;

public class Partie implements Serializable{
    
    /* Attributs */

    /**
     * Hauteur de la grille
     */
    public final static int hauteur = 10;

    /**
     * Largeur de la grille
     */
    public final static int largeur = 10;


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

        this.grille = new char [Partie.largeur][Partie.hauteur];
        this.piaPosees = listePieceAlea(5); // Nombre de pièces pourrait dépendre d'une variable joueur static dans jeu
        this.piPosees = new ArrayList<Piece>();
    }


    public ArrayList<Piece> listePieceAlea(int taille) {
        ArrayList<Piece> tab = new ArrayList<Piece>();
        for (int i = 0; i < taille; i++) {
            int nbAlea = (int) Math.random()*4;
            switch (nbAlea) {
                case 0:
                    tab.add(new C(0, 0));
                    break;
                case 1:
                    tab.add(new L(0, 0));
                    break;
                case 2:
                    tab.add(new T(0, 0));
                    break;
                default:
                    tab.add(new U(0, 0));
                    break;
            }
        }
        return tab;
    }

    public boolean debordeGrille(Piece p, int x, int y) {
        ArrayList<Carre> lCarre = p.getListe();

        int largMax = 0;
        int largMin = Partie.largeur;
        int hautMax = 0;
        int hautMin = Partie.hauteur;
        for (Carre carre : lCarre) {
            if(carre.getX() > largMax)
                largMax = carre.getX();
            if(carre.getX() < largMin)
                largMin = carre.getX();
            if(carre.getY() > hautMax)
                hautMax = carre.getY();
            if(carre.getY() < hautMin)
                hautMin = carre.getY();
        }

        /* On vérifie si des carrés de la pièce dépassent d'un des bord,
            on retourne true */
        if(largMax + x > Partie.largeur)
            return true;
        if(largMin + x < 0)
            return true;
        if(hautMax + y > Partie.hauteur)
            return true;
        if(hautMin + y < 0)
            return true;

        return false;


    }

    public boolean pieceSuperposee(Piece p) {
        for (Piece p2 : this.piPosees) {
            if (p.superpose(p2)) {
                return true;
            }
        }
        return false;
    }


    public void ajouterPiece(Piece p, int x, int y) throws CaseDejaRemplieException, PieceDebordeException {

        try {
            if (debordeGrille(p, x, y)) {
                throw new PieceDebordeException("La piece deborde de la grille de jeu");
            }
            else if(pieceSuperposee(p)) {
                throw new CaseDejaRemplieException("Deux carres se superposent");
            }
        } finally {
            this.grille[x][y] = p.getId();
        }
    }


    public double nbPiPosees() {
        return (double) this.piPosees.size();
    }

}
