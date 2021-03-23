public class C extends Piece{

    public C(int x,int y){
        this.setNomFichier("pieces/C.txt");
        try{
            this.lireFichier();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setId('C');
        this.setX(x);
        this.setY(y);
    }
}