import java.io.*;
public class Avance extends Joueur implements Serializable{

    public Avance(String nom){
        this.nom=nom;
    }

    public double calculerScore(){
        return 2*this.liste.get(Jeu.numeroPartie).nbPiPosees();
    }

}