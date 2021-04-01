import java.util.*;
import java.io.*;

public class Jeu{


    public static ArrayList<Joueur> listeJoueur=new ArrayList<Joueur>();
    public static Joueur joueurCharge;
    public static int numeroPartie;

    public static void chargerListeJoueur(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("donnes.dat"));
            //on est obligé de faire cela car sinon le programme ne veut pas compiler alors qu'il est juste
            @SuppressWarnings("unchecked")
            ArrayList<Joueur> inter=(ArrayList<Joueur>) ois.readObject();
            Jeu.listeJoueur = new ArrayList<Joueur>(inter);
            ois.close();
        }catch (IOException ioe){
            System.out.println("ALERTE : Une erreur dans le chargement (erreur fichier)");
        }catch (ClassNotFoundException cnf){
            System.out.println("ALERTE : Une erreur dans le chargement (erreur classe)");
        }
    }

    public static void sauvegarder(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("donnes.dat"));
            oos.writeObject(Jeu.listeJoueur);
            oos.close();
        }catch (IOException ioe){
            System.out.println("ALERTE : Une erreur dans la sauvegarde (erreur fichier)");
        }
    }


    public static void main(String[] args){
        
    }
}