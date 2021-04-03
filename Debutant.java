import java.io.*;
public class Debutant extends Joueur implements Serializable{

    public Debutant(String nom){
        super(nom);
    }

    public double calculerScore(){
        Jeu.game.setScore((Jeu.game).nbPiPosees());
        return Jeu.game.getScore();
    }

}