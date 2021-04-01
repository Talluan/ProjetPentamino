public class X extends Piece {
    
    public X(int x,int y){
        super(x,y);
        this.setNomFichier("pieces/X.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('X');
    }
}
