package fr.ensicaen.util.generateurBiocode;

import java.util.ArrayList;

/** 
* @author Yassir Mohamed Bouhaddaoui  yassir.bouhaddaoui@gmail.com
*/


public class GramShmitt {
	

	
 /** 
  * afficher une matrice.
  * @param copy matrice à afficher
  */	
	
public void afficher(Matrice copy){  
		
		
		for(int i =0 ; i<copy.getRow() ; i++){
			
			for(int j=0 ; j<copy.getCol() ; j++){
				System.out.print(copy.getVecteurs()[i][j]+" ");
			}
			System.out.println();
		}
	}

/** 
 * afficher une matrice version 2.
 * @param vecteur matrice à afficher
 */	
	
public void afficher(int vecteur[][]){  
	
	System.out.print("biocode = ");
	for(int i =0 ; i<vecteur.length ; i++){
		
		for(int j=0 ; j<vecteur[0].length ; j++){
			System.out.print(vecteur[i][j]+"");
		}
		System.out.println();
	}
}
	
/** 
 * extraire un vecteur  d'une matrice.
 * @param m matrice 
 * @param m col du vect à extraire 
 */

public Matrice extraireVect(Matrice m,int col){
	
	 Matrice Mat = new Matrice(m.getRow(),1);
	 
	 for(int i=0;i<m.getRow();i++){
		Mat.getVecteurs()[i][0] = m.getVecteurs()[i][col];
		 
	 }
	return Mat;
	
}




/** 
 * transposer un vecteur.
 * @param m matrice à transposer 
 * il faut extraire un vecteur ( matrice 1 ligne  n col) et apres le transposer sinon bug !!!
 */

Matrice tarnsposerVect(Matrice m){
	
    Matrice Mat = new Matrice(1,m.getRow());
	 
	for(int i=0 ;i<m.getRow() ; i++){
		
		for(int j =0;j<m.getCol(); j++){
			
			Mat.getVecteurs()[j][i]=m.getVecteurs()[i][j];
		}
	}
	

	
	return Mat;
	
	
}

/** 
* normaliser un vecteur
* 
* @param vecteur à normaliser
* @param 
*/

public double normalisation(Matrice a){
	
	
	
	 double scalaire=Math.sqrt(MultiplyMatrice(tarnsposerVect(a), a).getVecteurs()[0][0]); // seulement dans ce cas [0][0]

	return scalaire;
	
}
	



/** 
* calcule des vecteurs (algo proj)
* 
* @param matrice
* @param 
*/

public Matrice calculeVecteur(Matrice a){

	ArrayList<Matrice> listQ = new ArrayList<Matrice>();
	
	Matrice firstVect = MultiplyMatriceScalaire(extraireVect(a,0), (double)1/normalisation(extraireVect(a,0)));
    listQ.add(firstVect);
	
	for(int i=1;i<a.getCol();i++){
		
		Matrice extractV= extraireVect(a,i);
		
		Matrice Somme= new Matrice(extractV.getRow(),extractV.getCol());	
		
		for(int j=0;j<listQ.size();j++){
		
			Somme=addMatrice(Somme,projectVect(extractV,listQ.get(j)));
			
		}
		
	    Matrice vectmp =soustractionMatrice(extractV, Somme);
		Matrice Vect= MultiplyMatriceScalaire(vectmp,(double)1/normalisation(vectmp));
	    listQ.add(Vect);
	    
	}

	Matrice newo = new Matrice(a.getRow(),a.getCol());
	
	for(int p=0;p<listQ.size();p++){
		
		ConstrMatriceGram(listQ.get(p), newo, p);
		
	}
	
	
	return newo;
	
	
}



/** 
* calcule de la projection du vecteur vect
* calcule de tr(vet)*q1
* @param vecteur 2  
* @param vecteur 1 de la matrice
*/

public Matrice projectVect(Matrice vect,Matrice vectq){
	
	
	Matrice trVect = tarnsposerVect(vect);
	
	Matrice mul = MultiplyMatrice(trVect, vectq);
	
	Matrice tmpres=MultiplyMatriceScalaire(vectq,mul.getVecteurs()[0][0]);
	
	return tmpres;
	
}


/** 
*construire la nouvelle matrice de gram
*/


public Matrice ConstrMatriceGram(Matrice vect,Matrice newOne,int indiceCol){
		
	for(int i=0;i<vect.getRow();i++){
		newOne.getVecteurs()[i][indiceCol]=vect.getVecteurs()[i][0];
	}
	
		
	return newOne;
}




/** 
* soustraction de deux matrices
*
* @param matrice 1
* @param Matrice 2
*/

public Matrice soustractionMatrice(Matrice a,Matrice b){
	
	Matrice soust = new Matrice(a.getRow(),a.getCol());
	   
	  for(int i = 0; i <a.getRow(); i++) { 
	    for(int j = 0; j <a.getCol(); j++) { 
	    	
	    	soust.getVecteurs()[i][j]=a.getVecteurs()[i][j] - b.getVecteurs()[i][j];
	    }  
	  }
	   
return soust;
	

}

/** 
* Addition de 2 matrices 
* @param matrice 1
* @param matrice 2
*/

public Matrice addMatrice(Matrice a,Matrice b){
	
	Matrice soust = new Matrice(a.getRow(),a.getCol());
	   
	  for(int i = 0; i <a.getRow(); i++) { 
	    for(int j = 0; j <a.getCol(); j++) { 
	    	
	    	soust.getVecteurs()[i][j]=a.getVecteurs()[i][j] + b.getVecteurs()[i][j];
	    }  
	  }
	   
return soust;
	

}


/** 
* multiplication de deux matrices

* @param matrice 1
* @param matrice 2
*/

public Matrice MultiplyMatrice(Matrice a,Matrice b){
	
	Matrice tmp = new Matrice(a.getRow(),b.getCol());
	
	 int aRows = a.getRow();
     int aColumns = a.getCol();
     int bRows = b.getRow();
	 int bColumns = b.getCol();
		   
		  if ( aColumns != bRows ) {
		    throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
		  }
		   
		   
		  for(int i = 0; i < aRows; i++) { // aRow
		    for(int j = 0; j < bColumns; j++) { // bColumn
		      for(int k = 0; k < aColumns; k++) { // aColumn
		        tmp.getVecteurs()[i][j] += a.getVecteurs()[i][k] * b.getVecteurs()[k][j];
		      }
		    }  
		  }
		   
	return tmp;
	
}
	

/** 
* multiplication d'une matrice par un scalaire
* 
* @param matrice 
* @param scalaire
*/

public Matrice MultiplyMatriceScalaire(Matrice a,double scalaire){
	
	Matrice tmp = new Matrice(a.getRow(),a.getCol());
		   
		  for(int i = 0; i <a.getRow(); i++) { 
		    for(int j = 0; j <a.getCol(); j++) { 
		    	
		    	tmp.getVecteurs()[i][j]=a.getVecteurs()[i][j]*scalaire;
		    }  
		  }
		   
	return tmp;
	
}
	
}
