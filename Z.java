public class Z extends Piece {
    
    public Z(int x,int y){
        super(x,y);
        this.setNomFichier("pieces/Z.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('Z');
    }
}
