import java.io.*;
public class Avance extends Joueur implements Serializable{

    public Avance(String nom){
        super(nom);
    }

    public double calculerScore(){
        Jeu.game.setScore(2*(Jeu.game).nbPiPosees());
        return Jeu.game.getScore();
    }

}