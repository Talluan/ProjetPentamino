import java.io.*;
public class Debutant extends Joueur implements Serializable{

    public Debutant(String nom){
        super(nom);
    }

    public double calculerScore(){
        return ((Partie)(this.getListe().get(Jeu.numeroPartie))).nbPiPosees();
    }

}