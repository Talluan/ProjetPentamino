import java.util.*;
import java.io.*;
public class Piece implements Serializable{
	/** Les coordonées de la pièce */
	private int x,y;
	/** le caractère correspondant à la pièce */
	private char id;
	/** la liste de carré d'une pièce */
	private ArrayList<Carre> liste;
	private String nomFichier;
	
	public void LireFichier() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(this.nomFichier));
		String ligne = br.readLine();
		char carre;
		int hauteur=0;
		while(ligne!=null){
			for(int i=0;i<ligne.length();i++){
				carre=ligne.charAt(i);
				if(carre=='#'){
					this.liste.add(new Carre(i,hauteur));
				}
				
			}
			hauteur++;
			ligne=br.readLine();
		}
		
	}
	
	
	public boolean superpose(Piece p){
		boolean res=false;
		Carre c1;
		for(int i=0;i<this.liste.size();i++){
			c1=(Carre)this.liste.get(i);
			for(int j=0;j<p.liste.size();j++){
				if(c1.equals(p.liste.get(j))){
					res=true;
				}
			}
		}
		return res;
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
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public char getId(){
		return this.id;
	}
	public ArrayList getListe(){
		return this.liste;
	}
}
