public class S extends Piece {
    
    public S(int x,int y){
        super(x,y);
        this.setNomFichier("pieces/S.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('S');
    }
}
