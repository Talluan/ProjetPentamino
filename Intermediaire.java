import java.io.*;
public class Intermediaire extends Joueur implements Serializable{

    public Intermediaire(String nom){
        this.nom=nom;
    }

    public double calculerScore(){
        return 1.5*this.liste.get(Jeu.numeroPartie).nbPiPosees();
    }

}