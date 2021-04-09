import java.io.*;

public class Carre implements Serializable{
	/** Les coordonnées du carré*/
	private int x,y;
	
	/**
	Constructeur de Carre prenant des coordonnées
	@param x X du carré
	@param y Y du carré
	*/
	public Carre(int x,int y){
		this.x=x;
		this.y=y;
	}

	/**
	Constructeur de Carre faisant une copie d'un autre Carre
	@param car Carre que l'on veut copier
	*/
	public Carre(Carre car){
		this.x=car.x;
		this.x=car.y;
	}
	
    /**
     * méthode qui compare 2 carrés
     * @param c carré à comparer
     * @return un booléen qui indique si les carrés ont les mêmes coordonnées
     */
	public boolean equals(Carre c){
		return c.x==this.x && c.y==this.y;
	}
	
    /**
     * initialise la position en abscisse
     * @param x position
     */
	public void setX(int x){
		this.x=x;
	}

    /**
     * initialise la position en ordonnée
     * @param y position
     */
	public void setY(int y){
		this.y=y;
	}

    /**
     * méthode qui initialise les 2 positions
     * @param x position abscisse
     * @param y position ordonnée
     */
	public void setXY(int x,int y){
		this.x=x;
		this.y=y;
	}
	
    /**
     * retourne la position en x
     * @return position en abscisse
     */
	public int getX(){
		return this.x;
	}

    /**
     * retourne la position en y
     * @return position en ordonnée
     */
	public int getY(){
		return this.y;
	}
	
    /**
     * méthode donnant les infos sur un carré
     * @return chaine de caractères donnant les informations sur le carré
     */
	public String toString(){
		return "("+this.x+","+this.y+")";
	}
}
