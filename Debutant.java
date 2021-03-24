import java.io.*;
public class Debutant extends Joueur implements Serializable{

    public Debutant(String nom){
        this.nom=nom;
    }

    public double calculerScore(){
        return this.liste.get(Jeu.numeroPartie).nbPiPosees();
    }

}