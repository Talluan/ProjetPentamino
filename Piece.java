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
        this.x=x;
        this.y=y;
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
			for(int i=0;i<ligne.length();i++) {
				carre=ligne.charAt(i);
				if(carre=='#') {
					this.liste.add(new Carre(i,haut));
				}
				
			}
			haut++;
			ligne=br.readLine();
		}

        this.largeur = larg;
        this.hauteur = haut;
	}
    /**
     * méthode qui indique si 2 pièces ont des formes similaires
     * @return booléen qui indique si elles ont la même forme ou non
     */
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
	

    /**
     * méthode qui vérifie si deux pièces ont des carrés superposés
     * @param p Pièce que l'on souhaite vérifier
     * @return booléen indiquant si elles sont superposées ou non
     */
	public boolean superpose(Piece p,int x,int y){
        for(Carre c1 : p.liste){
            for(Carre c2 : this.liste){
                if( (c1.getX()+x)==(c2.getX()+this.x) && (c1.getY()+y)==(c2.getY()+this.y) ){
                    return true;
                }
            }
        }
        return false;
	}

    /**
     * méthode qui indique si deux pièces ont la même origine
     * @param p pièce à vérifier
     * @return booléen qui dit si les pièces ont la même origine
     */
	public boolean memeOrigine(Piece p){
		return (this.x==p.x) && (this.y==p.y);
	}
	
    /**
     * méthode qui affiche une pièce en entier
     */
    public void afficherPiece() {
        System.out.println(this.id);
        boolean trouve;
        String chaine="";
        int cy=0;
        for(int i=0;i<this.hauteur;i++){
            for(int j=0;j<this.largeur;j++){
                trouve = false;
                for(Carre c :this.liste){
                    if(c.getX()==j && c.getY()==i){
                        trouve=true;
                    }
                }
                if(trouve){
                    chaine+="#";
                    trouve=false;
                }else{
                    chaine+=".";
                }
            }
            chaine+="\n";
        }
        System.out.println(chaine);
    }

    /**
     * méthode qui retourne la liste de carrés d'une pièce ne dépassant pas de la grille de jeu et n'étant pas superposés
     * @param x position en abscisse de la pièce
     * @param y position en ordonnée de la pièce
     * @return  liste des carrés à placer
     */
	public ArrayList<Carre> CarresPlacable( int x, int y) {
        ArrayList<Carre> res = this.CarresNonSuperposes(x, y);
        for (Carre carre : this.liste) {
            if (carre.getX() + x < Partie.largeur && carre.getY() + y < Partie.hauteur) {
                res.add(carre);
            }
        }
        return res;
    }

    /**
     * méthode qui retourne la liste de carrés placable, ils ne sont pas superposés à une autre pièce
     * @param x position en abscisse de la pièce
     * @param y position en ordonnée de la pièce
     * @return  liste des carrés à placer
     */
	public ArrayList<Carre> CarresNonSuperposes( int x, int y) {
        ArrayList<Carre> res = new ArrayList<Carre>();
        for (Carre carre : this.liste) {
            if (carre.getX() + x < Partie.largeur && carre.getY() + y < Partie.hauteur) {
                if(Jeu.game.getGrille()[carre.getY() + y][carre.getX() + x] == '.') {
                    res.add(carre);
                }
            }
        }
        return res;
    }


	/**
     * méthode qui initialise la position horizontale d'une pièce
     * @param nx position horizontale
     */
	public void setX(int nx){
		this.x=nx;
	}

    /**
     * méthode qui initialise la position verticale d'une pièce
     * @param ny position verticale
     */
	public void setY(int ny){
		this.y=ny;
	}

    /**
     * méthode qui initialise la position verticale et horizontale d'une pièce
     * @param nx position horizontale
     * @param ny position verticale
     */
	public void setXY(int nx,int ny){
		this.x=nx;
		this.y=ny;
	}

    /**
     * méthode qui initialise l'ID d'une pièce
     * @param nid id de la pièce
     */
	public void setId(char nid){
		this.id=nid;
	}

    /**
     * méthode qui initialise la liste de carrés d'une pièce
     * @param ar liste de Carrés
     */
    public void setListe(ArrayList<Carre> ar) {
        this.liste = ar;
    }

    /**
     * méthode qui initialise le nom du fichier source
     * @param nnom nom du fichier source
     */
	public void setNomFichier(String nnom){
		this.nomFichier=nnom;
	}

    /**
     * méthode qui retourne la position horizontale d'une pièce
     * @return x
     */
	public int getX(){
		return this.x;
	}

    /**
     * méthode qui retourne la position verticale d'une pièce
     * @return y
     */
	public int getY(){
		return this.y;
	}

    /**
     * méthode qui retourne l'ID d'une pièce
     * @return id
     */
	public char getId(){
		return this.id;
	}

    /**
     * méthode qui retourne la liste de carrés de la pièce
     * @return liste
     */
	public ArrayList<Carre> getListe(){
		return this.liste;
	}
}
