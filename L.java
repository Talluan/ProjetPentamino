public class L extends Piece{

    public L(int x,int y){
        this.setNomFichier("pieces/L.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('L');
        this.setX(x);
        this.setY(y);
    }
}