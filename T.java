import java.util.ArrayList;

public class T extends Piece{

    public T(int x,int y){
        super(x,y);
        this.setListe(new ArrayList<Carre>());
        this.setNomFichier("pieces/T.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('T');
    }
}