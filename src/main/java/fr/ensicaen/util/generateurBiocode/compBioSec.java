package fr.ensicaen.util.generateurBiocode;


public class compBioSec {

	
	
	
	/** 
	* distance hamming entre deux biocode
	* 
	* @param biocode 1 
	* @param biocode 2
	* int taille du biocode
	*/

	
	public int Hamming(String bio1,String bio2,int taille){
		
		int[] ham1= new int[taille];
		int[] ham2= new int[taille];
		int count =0;
		
		ham1=fromStrtoTable(bio1);
		ham2=fromStrtoTable(bio2);
		
		
		if (ham1.length != ham2.length)
        {
            return -1;
        }
		
		for (int i = 0; i < ham1.length; i++)
        {
            if (ham1[i] != ham2[i])
               count++;
        }
		//System.out.println("count--"+count);
		return taille-count;
		
		
		
		
	}
	
	
	/** 
	* convertir une chaine en un tableau
	* 
	* @param chaine à convertir
	* 
	*/


	public int [] fromStrtoTable(String str){
		 
		
		 char[] chars=str.toCharArray();

		    int[] characters=new int[chars.length];
		    
		    for (int i = 0; i < chars.length; i++) {
		        characters[i]=Integer.parseInt(String.valueOf(chars[i]).toString());
		       // System.out.println(chars[i]);
		    }
		
		
		
		return characters;
	
	}
		
		
	/** 
	* calcule le pourcentage de la fonction haming 
	* 
	* @param count : distance hamming
	* @param taille : taille du biocode  
	* 
	*/

	public double pourcentageHaming(int count,int taille){
		
		
		return (double)count/taille;
		
	}


	
	
}
