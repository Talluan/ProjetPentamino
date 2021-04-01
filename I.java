public class I extends Piece{

    public I(int x,int y){
        super(x,y);
        this.setNomFichier("pieces/I.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('I');
    }
}
