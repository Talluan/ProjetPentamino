import java.util.ArrayList;

import java.util.ArrayList;

public class C extends Piece{

    public C(int x,int y){
        super(x,y);
        this.setListe(new ArrayList<Carre>());
        this.setNomFichier("pieces/C.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('C');
    }
}