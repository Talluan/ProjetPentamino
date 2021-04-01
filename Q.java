public class Q extends Piece {

    public Q(int x,int y){
        super(x,y);
        this.setNomFichier("pieces/Q.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('Q');
    }
}
