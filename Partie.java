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
     * Score de la partie
     */
    private double score;



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
     * méthode qui ajoute une pièce à poser dans la liste
     * @param n nombre de pièces à ajouter
     */
    public void addPieceAlea(int n) {
        for (int i = 0; i < n; i++) {
            int nbAlea = (int) (Math.random()*10);
            switch (nbAlea) {
                case 0:
                    this.piaPosees.add(new C(0, 0));
                    break;
                case 1:
                    this.piaPosees.add(new L(0, 0));
                    break;
                case 2:
                    this.piaPosees.add(new T(0, 0));
                    break;
                case 3:
                    this.piaPosees.add(new I(0, 0));
                    break;
                case 4:
                    this.piaPosees.add(new P(0, 0));
                    break;
                case 5:
                    this.piaPosees.add(new Q(0, 0));
                    break;
                case 6:
                    this.piaPosees.add(new S(0, 0));
                    break;
                case 7:
                    this.piaPosees.add(new X(0, 0));
                    break;
                case 8:
                    this.piaPosees.add(new Z(0, 0));
                    break;
                default:
                    this.piaPosees.add(new U(0, 0));
                    break;
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
            int nbAlea = (int) (Math.random()*10);
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
                case 3:
                    tab.add(new I(0, 0));
                    break;
                case 4:
                    tab.add(new P(0, 0));
                    break;
                case 5:
                    tab.add(new Q(0, 0));
                    break;
                case 6:
                    tab.add(new S(0, 0));
                    break;
                case 7:
                    tab.add(new X(0, 0));
                    break;
                case 8:
                    tab.add(new Z(0, 0));
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
        if(largMax + x >= Partie.largeur)
            return true;
        if(largMin + x < 0)
            return true;
        if(hautMax + y >= Partie.hauteur)
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
    public boolean pieceSuperposee(Piece p,int x,int y) {
        for (Piece p2 : this.piPosees) {
            if (p2.superpose(p,x,y)) {
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

        // On vérifie si la pièce va déborder ou si elle va être superposée
        if(pieceSuperposee(p,x,y)) {
            throw new CaseDejaRemplieException("Deux carres se superposent");
        }
        if (debordeGrille(p, x, y)) {
            System.out.println("ca throw PieceDeborde");
            throw new PieceDebordeException("La piece deborde de la grille de jeu");
        }
        // On vérifie que la pièce est bien dans la liste d'éléments à poser
        int place = this.piaPosees.indexOf(p);
        if (place != -1) {
            this.piaPosees.remove(place);
            this.piPosees.add(p);

            // On ajoute les coordonnées à la pièce
            p.setXY(x, y);

            // On place la pièce sur la grille grâce à ses coordonnées relatives
            ArrayList<Carre> listeCarre = p.getListe();
            for (Carre carre : listeCarre) { 
                this.grille[y + carre.getY()][x + carre.getX()] = p.getId();
            }
        }
        
    }

    public void forcerPlacementDebutant(Piece p, int x, int y) {
        // On prend les carres qui rentrent bien dans la grille
        ArrayList<Carre> listeCarres = p.CarresPlacable(x, y);

        // On les place

        int place = this.piaPosees.indexOf(p);
        if (place != -1) {
            this.piaPosees.remove(place);
            this.piPosees.add(p);

            // On ajoute les coordonnées à la pièce
            p.setXY(x, y);

            // On place la pièce sur la grille grâce à ses coordonnées relatives, sans mettre celles qui dépassent
            for (Carre carre : listeCarres) { 
                this.grille[y + carre.getY()][x + carre.getX()] = p.getId();
            }
        }
    }

    public void forcerPlacementIntermediaire(Piece p, int x, int y) {
        // On prend les carres qui ne 
        ArrayList<Carre> listeCarres = p.CarresNonSuperposes(x, y);

        // On les place
        int place = this.piaPosees.indexOf(p);
        if (place != -1) {
            this.piaPosees.remove(place);
            this.piPosees.add(p);

            // On ajoute les coordonnées à la pièce
            p.setXY(x, y);

            // On place la pièce sur la grille grâce à ses coordonnées relatives, sans mettre celles qui dépassent
            for (Carre carre : listeCarres) { 
                this.grille[y + carre.getY()][x + carre.getX()] = p.getId();
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
            if (carre.getX() + x < Partie.largeur && carre.getY() + y < Partie.hauteur) {
                this.grille[y + carre.getY()][x + carre.getX()] = '.';
            }
        }

        // On redessine toutes les pièces sur la grille
        for(Piece pi : Jeu.game.piPosees){
            listeCarre = pi.getListe();
            for(Carre c : listeCarre){
                if (c.getX() + x < Partie.largeur && c.getY() + y < Partie.hauteur) {
                this.grille[pi.getY() + c.getY()][pi.getX() + c.getX()] = pi.getId();
                }
            }
        }

    }
    
    /**
     * méthode qui affiche la liste des pièces à poser
     */
    public void afficherListePiece() {
        for (int i = 0; i < this.piaPosees.size(); i++) {
            System.out.println("piece " + i + " :" );
            this.piaPosees.get(i).afficherPiece();
        }
    }


    /**
     * méthode qui affiche la grille de jeu
     */
    public void afficherGrille() {
        System.out.println("-----------------------");
        System.out.println("0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < Partie.largeur; i++) {
            for (int j = 0; j < Partie.hauteur; j++) {
                System.out.print(this.grille[i][j] + " ");
            }
            System.out.printf("  %d", i);
            System.out.println();
        }
    }

    /**
     * méthode qui affiche le nombre de pièces restantes à poser d'une partie
     */
    public void afficherDetails() {
        int nbPieces = this.piaPosees.size();
        System.out.println("Il reste " + nbPieces + " pieces a poser dans cette partie ! (Score : "+this.score+")");
    }

    /**
     * méthode qui indique le nombre de pièces posées
     * @return un double indiquant combien de pièces sont posées
     */
    public double nbPiPosees() {
        return (double) this.piPosees.size();
    }

    /**
     * méthode qui retourne la liste de pièces à poser
     * @return liste de pièces à poser
     */
    public ArrayList<Piece> getPiaPosees() {
        return this.piaPosees;
    }
    /**
     * méthode qui retourne le score actuel de la partie
     * @return un double le score de la partie
     */
    public double getScore(){
        return this.score;
    }

    /**
     * méthode qui change le score de la partie
     * @param sc le nouveau score
     */
    public void setScore(double sc){
        this.score=sc;
    }

    /**
     * méthode qui retourne la grille de jeu
     * @return grille de jeu
     */
    public char[][] getGrille() {
        return this.grille;
    }

}
