import java.io.*;
import java.util.Scanner;

/**
 * Classe Avance, hérite de joueur et correspond au niveau de jeu le plus élevé
 */
public class Avance extends Joueur implements Serializable{

    /**
     * Constructeur de Avance
     * @param nom nom du joueur
     */
    public Avance(String nom){
        super(nom);
    }
    
    /**
     * Méthode qui permet de poser une pièce sur la grille
     * @param piece pièce à placer
     * @return booléen qui indique si l'action a eu lieu ou non
     */
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
            System.out.println("Les pieces se superposent, vous ne pouvez pas la placer ici !"); 
            return false;
        } catch (PieceDebordeException e) {
            System.out.println("La piece sort de la grille, vous ne pouvez pas la placer ici !");
            return false;
        }
        // On rajoute une pièce aléatoire dans les pièces à poser
        Jeu.game.addPieceAlea(1);
        return true;
    }

    /**
     * méthode qui permet de calculer le score du joueur avancé
     * @return
     */
    public double calculerScore(){
        Jeu.game.setScore(2*(Jeu.game).nbPiPosees());
        return Jeu.game.getScore();
    }
}