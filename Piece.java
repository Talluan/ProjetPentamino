import java.util.*;
import java.io.*;
public abstract class Piece implements Serializable{

	/** 
     * Les coordonées de la pièce 
     */
	private int x,y;

	/** 
     * le caractère correspondant à la pièce 
     */
	private char id;

	/** 
     * la liste de carré d'une pièce 
     * */
	private ArrayList<Carre> liste;

    /**
     * nom du fichier
     */
	private String nomFichier;

    /**
     * hauteur de la pièce
     */
    private int hauteur;

    /**
     * largeur de la pièce
     */
    private int largeur;


    /**
     * Constructeur de pièce
     * @param x position horizontale
     * @param y position verticale
     */
	public Piece(int x,int y){
        this.setX(x);
        this.setY(y);
    }
	

    /**
     * méthode qui lit un fichier stockant une pièce
     * @throws IOException 
     */
	public void lireFichier() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(this.nomFichier));
		String ligne = br.readLine();
		char carre;
		int haut=0;
        int larg=0;
		while(ligne!=null){

            // on récupère la largeur de la ligne si elle est plus grande que celle stockée
            if (larg < ligne.length()) {
                larg = ligne.length();
            }

            // si le caractère correspond à un # dans le fichier, ajoute un carre aux coordonnées
			for(int i=0;i<ligne.length();i++){
				carre=ligne.charAt(i);
				if(carre=='#'){
					this.liste.add(new Carre(i,hauteur));
				}
				
			}
			hauteur++;
			ligne=br.readLine();
		}

        this.largeur = larg;
        this.hauteur = haut;
	}
	
    
    public boolean similaire(Piece p) {
        if (this.liste.size() != p.liste.size()) {
            return false;
        }
        for (Carre carre : liste) {
            boolean jum = false;
            for (Carre carre2 : p.liste) {
                if (carre.getX() == carre2.getX() && carre.getY() == carre2.getY()) {
                    jum = true;
                }
            }
            if (!jum) {
                return false;
            }
        }
        return true;
    }
	
	public boolean superpose(Piece p){
		Carre c1;
		for(int i=0;i<this.liste.size();i++){
			c1=(Carre)this.liste.get(i);
			for(int j=0;j<p.liste.size();j++){
				if(((c1.getX()+this.x)==(p.x+p.liste.get(i).getX()))&&((c1.getY()+this.y)==(p.y+p.liste.get(i).getY()))){
					return true;
				}
			}
		}
		return false;
	}

	public boolean memeOrigine(Piece p){
		return (this.x==p.x) && (this.y==p.y);
	}
	
    
    public void afficherPiece() {
        char[][] tab = new char[this.largeur][this.hauteur];

        // remplit un tableau avec des # ou des .
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                boolean trouve = false;
                for (Carre carre : this.liste) {
                    if(carre.getX() == i && carre.getY() == j) {
                        tab[i][j] = '#';
                        trouve = true;
                        break;
                    }
                }
                if (!trouve) {
                    tab[i][j] = '.';
                }
            }
        }

        // affiche le tableau rempli
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.println(tab[i][j]);
            }
        }
    }

	
	
	public void setX(int nx){
		this.x=nx;
	}
	public void setY(int ny){
		this.y=ny;
	}
	public void setXY(int nx,int ny){
		this.x=nx;
		this.y=ny;
	}
	public void setId(char nid){
		this.id=nid;
	}
	public void setNomFichier(String nnom){
		this.nomFichier=nnom;
	}

	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public char getId(){
		return this.id;
	}
	public ArrayList<Carre> getListe(){
		return this.liste;
	}
}
