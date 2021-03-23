public class T extends Piece{

    public T(int x,int y){
        this.setNomFichier("pieces/T.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('T');
        this.setX(x);
        this.setY(y);
    }
}