import java.util.*;
import java.io.*;
public abstract class Joueur implements Serializable{
    private String nom;
    private double scoreMoyen;
    private ArrayList<Partie> liste;


    public static Joueur chargerJoueur(String nom){
        
    }

    public boolean poserPiece(Partie partie,Piece piece){
        try{
            partie.poserPiece(piece);
        }catch (Exception e){//le type de l'exeption va changer là c'est si les pices se superposent
            partie.retirerDernierePiece();
            System.out.println("Les pieces se superposent");
        }
        
    }

    
}