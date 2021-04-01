import java.util.*;
import java.io.*;

public class Jeu{


    public static ArrayList<Joueur> listeJoueur;
    public static Joueur joueurCharge;
    public static int numeroPartie;

    public void chargerListeJoueur(){
        try{
            ObjectInputStream fis = new ObjectInputStream(new FileInputStream("donnes.dat"));
            Jeu.listeJoueur=(ArrayList<Joueur>) fis.readObject();
            fis.close();
        }catch (IOException ioe){
            System.out.println("ALERTE : Une erreur dans le chargement (erreur fichier)");
        }catch (ClassNotFoundException cnf){
            System.out.println("ALERTE : Une erreur dans le chargement (erreur classe)");
        }
    }


    public static void main(String args){
        System.out.println("bruh");
    }
}