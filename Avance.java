import java.io.*;
public class Avance extends Joueur implements Serializable{

    public Avance(String nom){
        super(nom);
    }

    public double calculerScore(){
        return 2*this.getListe().get(Jeu.numeroPartie).nbPiPosees();
    }

}