package fr.ensicaen.util.generateurBiocode;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class RandomVector {


	
	
	private Matrice randV;

	
	
	
	public RandomVector(int row,int col){
		
		randV = new Matrice(row,col);
	
	}
	
	
	 /** 
	  * generer la matrice ds vecteurs
	  * les nombres sont entre 0 et 1000000000 (à changer si vous voulez)
	  */	
		
	
	public void generateMatrice(){
		
		 for (int i = 0; i < randV.getRow(); i++ ) {
				for (int j = 0; j < randV.getCol(); j++ ) {
				    randV.getVecteurs()[i][j] =(int) (Math.random() * ( 400 - 0 )); 
			    } 		
	     }
		
	}


	
	
	 /** 
	  * Enregistrer les vecteurs dans un fichiers text 
	  * @param fichier : nom du fichier
	  */	
			
	
	public void EnregistrerVector(String fichier){
		String chaine="";
		
		try {
			
			FileWriter fw = new FileWriter (fichier);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw);
			 for (int i = 0; i < randV.getRow(); i++ ) {
					for (int j = 0; j < randV.getCol(); j++ ) {
					    chaine = chaine+ randV.getVecteurs()[i][j]+" "; 
				    } 		
					//System.out.println(chaine);
				    fichierSortie.println (chaine);
				    chaine = "";
		     }

			fichierSortie.close();
			System.out.println("Le fichier " + fichier + " a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	
	/** 
	 * Conversion d'une string en une matrice 1 ligne  n colonnes 
	 * faire attention à la taille col bug possible si taille differente
	 * @param charMat : la chaine lu 
	 * @param int i : ligne 
	 * @param int col : colonnes
	 * */

	
	public void getKeyFromFile(String charPQ,int i,int col){
		
		StringTokenizer st = new StringTokenizer(charPQ);
		col =0;
		while (st.hasMoreElements()) {
			
			randV.getVecteurs()[i][col]=Double.parseDouble((String) st.nextElement());
			col++;
		}

	}
	
	 /** 
	  * lire un vecteur à partir d'un fichier text 
	  * bug possible si taille vect differente
	  * @param fichier : nom du fichier
	  */
	
	
		public void lireVector(String fichier){
			
			String ligne="";
			String chaine="";
			
			try{
				InputStream ips=new FileInputStream(fichier); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				int i=0;
				while ((ligne=br.readLine())!=null){
					chaine=ligne;
					//System.out.println(chaine);
					getKeyFromFile(chaine, i, 0);
					i++;
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			
			
		}
	
	public Matrice getRandV() {
		return randV;
	}




	public void setRandV(Matrice randV) {
		this.randV = randV;
	}




}
