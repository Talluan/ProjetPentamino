import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
     * Constructeur
     */
    public Partie() {

        this.grille = new char [Partie.largeur][Partie.hauteur];
        this.remplirGrille(); // On remplit la grille de .
        this.piaPosees = this.listePieceAlea(5); // Nombre de pièces pourrait dépendre d'une variable joueur static dans jeu
        this.piPosees = new ArrayList<Piece>();
    }


    /**
     * méthode chargée de remplir la grille d'une partie
     */
    public void remplirGrille() {
        for (int i = 0; i < Partie.largeur; i++) {
            for (int j = 0; j < Partie.hauteur; j++) {
                this.grille[i][j] = '.';
            }
        }
    }

    /**
     * méthode qui retourne une liste de pièce aléatoire
     * @param taille nombre de pièce que l'on veut dans notre liste
     * @return la liste de pièce
     */
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


    /**
     * méthode qui indique si une pièce déborde ou non
     * @param p piece que l'on souhaite poser
     * @param x position en largeur de la pièce
     * @param y position en hauteur de la pièce
     * @return un booleen indiquant si la pièce dépasse de la grille
     */
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


    /**
     * méthode qui indique si un pièce est superposée à une autre
     * @param p pièce que l'on souhaite vérifier
     * @return un booléen indiquant si la pièce est superposée
     */
    public boolean pieceSuperposee(Piece p) {
        for (Piece p2 : this.piPosees) {
            if (p.superpose(p2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * méthode qui ajoute une pièce à la grille de jeu
     * @param p pièce que l'on souhaite ajouter
     * @param x position hozizontale
     * @param y position verticale
     * @throws CaseDejaRemplieException
     * @throws PieceDebordeException
     */
    public void ajouterPiece(Piece p, int x, int y) throws CaseDejaRemplieException, PieceDebordeException {

        try {
            if (debordeGrille(p, x, y)) {
                throw new PieceDebordeException("La piece deborde de la grille de jeu");
            }
            else if(pieceSuperposee(p)) {
                throw new CaseDejaRemplieException("Deux carres se superposent");
            }
        } finally {
            // On vérifie que la pièce est bien dans la liste d'éléments à poser
            int place = this.piaPosees.indexOf(p);
            if (place != -1) {
                this.piPosees.remove(place);
                this.piPosees.add(p);

                // On ajoute les coordonnées à la pièce
                p.setXY(x, y);

                // On place la pièce sur la grille grâce à ses coordonnées relatives
                ArrayList<Carre> listeCarre = p.getListe();
                for (Carre carre : listeCarre) { 
                    this.grille[x + carre.getX()][y + carre.getY()] = p.getId();
                }
            }
        }
    }

    /**
     * méthode qui permet de retirer la dernière pièce de la grille de jeu
     * et la remet dans les pièces à poser
     */
    public void retirerDernierePiece() {
        // On récupère la place de la dernière pièce ajoutée
        int place = this.piPosees.size() - 1; 
        Piece p = this.piPosees.get(place);
        this.piPosees.remove(place);
        this.piaPosees.add(p);
        
        // On récupère les coordonnées de la pièce
        int x = p.getX();
        int y = p.getY();

        // On place des . aux emplacements de la pièce
        ArrayList<Carre> listeCarre = p.getListe();
        for (Carre carre : listeCarre) { 
            this.grille[x + carre.getX()][y + carre.getY()] = '*';
        }


    }
    
    /**
     * méthode qui affiche la liste des pièces à poser
     */
    public void afficherListePiece() {
        for (int i = 0; i < this.piaPosees.size(); i++) {
            System.out.println("piece " + i + 1 + " :" );
            this.piaPosees.get(i).afficherPiece();
        }
    }


    /**
     * méthode qui affiche la grille de jeu
     */
    public void afficherGrille() {
        for (int i = 0; i < Partie.largeur; i++) {
            for (int j = 0; j < Partie.hauteur; j++) {
                System.out.println(this.grille[i][j]);
            }
        }
    }



    /**
     * méthode qui indique le nombre de pièces posées
     * @return un double indiquant combien de pièces sont posées
     */
    public double nbPiPosees() {
        return (double) this.piPosees.size();
    }

}
