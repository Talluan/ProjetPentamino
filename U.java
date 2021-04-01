public class U extends Piece{

    public U(int x,int y){
        super(x,y);
        this.setNomFichier("pieces/U.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('U');
    }
}