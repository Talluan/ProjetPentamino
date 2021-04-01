public class P extends Piece {
    
    public P(int x,int y){
        super(x,y);
        this.setNomFichier("pieces/P.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('P');
    }
}
