import java.io.*;
public class Intermediaire extends Joueur implements Serializable{

    public Intermediaire(String nom){
        super(nom);
    }

    public double calculerScore(){
        Jeu.game.setScore(1.5*(Jeu.game).nbPiPosees());
        return Jeu.game.getScore();
    }

}