import java.io.*;
import java.util.Scanner;

/**
 * Classe Debutant, hérite de joueur et correspond au niveau de jeu le plus bas
 */
public class Debutant extends Joueur implements Serializable{

    /**
     * Constructeur de Debutant
     * @param nom nom du joueur
     */
    public Debutant(String nom){
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
            System.out.println("Les pieces se superposent, mais la partie continue !"); 
            // On force tout de même le placement car on est en débutant
            Jeu.game.forcerPlacementDebutant(piece, x, y); 
        } catch (PieceDebordeException e) {
            System.out.println("La piece sort de la grille, mais la partie continue !");
            // On force tout de même le placement car on est en débutant
            Jeu.game.forcerPlacementDebutant(piece, x, y);

        }
        // On rajoute une pièce aléatoire dans les pièces à poser
        Jeu.game.addPieceAlea(1);
        return true;
    }

    /**
     * Méthode qui retourne le score du joueur
     * @return score du joueur
     */
    public double calculerScore(){
        Jeu.game.setScore((Jeu.game).nbPiPosees());
        return Jeu.game.getScore();
    }

}