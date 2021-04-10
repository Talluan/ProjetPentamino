import java.io.*;
import java.util.Scanner;

/**
 * Classe Intermediaire, hérite de joueur et correspond au niveau de jeu moyen
 */
public class Intermediaire extends Joueur implements Serializable{

    /**
     * Constructeur de Intermediaire
     * @param nom nom du joueur
     */
    public Intermediaire(String nom){
        super(nom);
    }

    /**
     * Méthode qui permet de poser une pièce sur la grille
     * @param piece pièce à placer
     * @return booléen qui indique si l'action a eu lieu ou non
     */
    public boolean poserPiece(Piece piece) {
        System.out.println("Ou voulez vous placer la piece ?");
        System.out.print("En X ? : ");
        int x = Jeu.nextInt();
        System.out.print("En Y ? : ");
        int y = Jeu.nextInt();
        try {
            Jeu.game.ajouterPiece(piece, x, y);
        } catch (CaseDejaRemplieException e) { // le type de l'exeption va changer là c'est si les pièces se superposent
            System.out.println("Les pieces se superposent, on ne peut pas la placer ici !"); 
            return false;
        } catch (PieceDebordeException e) {
            System.out.println("La piece sort de la grille, mais la partie continue !");
            // On force tout de même le placement
            Jeu.game.forcerPlacementIntermediaire(piece, x, y);
        }
        // On rajoute une pièce aléatoire dans les pièces à poser
        Jeu.game.addPieceAlea(1);
        return true;
        
    }

    /**
     * Méthode qui permet de calculer le score du joueur
     * @return le score
     */
    public double calculerScore(){
        Jeu.game.setScore(1.5*(Jeu.game).nbPiPosees());
        return Jeu.game.getScore();
    }

}