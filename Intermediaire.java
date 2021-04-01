import java.io.*;
public class Intermediaire extends Joueur implements Serializable{

    public Intermediaire(String nom){
        super(nom);
    }

    public double calculerScore(){
        return 1.5*this.getListe().get(Jeu.numeroPartie).nbPiPosees();
    }

}