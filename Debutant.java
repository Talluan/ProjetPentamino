import java.io.*;
import java.util.Scanner;

public class Debutant extends Joueur implements Serializable{

    public Debutant(String nom){
        super(nom);
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
            System.out.println("Les pieces se superposent, mais la partie continue !"); 
            // On force tout de même le placement
            Jeu.game.forcerPlacementDebutant(piece, x, y); 
        } catch (PieceDebordeException e) {
            System.out.println("La piece sort de la grille, mais la partie continue !");
            // On force tout de même le placement
            Jeu.game.forcerPlacementDebutant(piece, x, y);

        }
        return true;
    }

    public double calculerScore(){
        Jeu.game.setScore((Jeu.game).nbPiPosees());
        return Jeu.game.getScore();
    }

}