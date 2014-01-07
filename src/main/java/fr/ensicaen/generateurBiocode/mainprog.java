package fr.ensicaen.generateurBiocode;



/** 
* @author Yassir Mohamed Bouhaddaoui  yassir.bouhaddaoui@gmail.com
*/




public class mainprog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		 /* generer template coté serveur  (enrolement)  */
		
		  Template tmp = new Template();
		  BioGenerator bioServeur = new BioGenerator();
		  String chem =tmp.lireFileTemplate("finger.txt");
		  Matrice fingerserveur =  tmp.getKeyFromFile(chem);
		  
		  
		// la fonction biocodgen genere la matrice des vect automatiquement
		// on peut la récuperer par  var.getRandomV()
		  
		   String biocodeServeur = bioServeur.biocodegenServeur(fingerserveur); // generation de biocode coté serveur 
		//
		  System.out.println("biocode serveur = "+bioServeur);
		  
		/*stocker les vecteurs ( dans un fichier  --dans la BD-- ) */ 
		  
		  bioServeur.getRandomV().EnregistrerVector("randomVector.txt");
		  		  
	   //coté client
		  
			Matrice randomVectClient;
			Matrice fingerClient;
		    Template tempclient = new Template();
		    
		    String chem2 =tempclient.lireFileTemplate("finger.txt");
			fingerClient = tempclient.getKeyFromFile(chem2);
		    
			RandomVector randV = new RandomVector(fingerClient.getCol(),20);
			randV.lireVector("randomVector.txt");
			randomVectClient = randV.getRandV();
		    
		    
		    BioGenerator bioClient =  new BioGenerator();
			String biocodeClient = bioClient.biocodegenClient(randomVectClient, fingerClient);
			System.out.println(biocodeClient);
			
			
			
			//comparaison des deux biocodes
			
		    compBioSec comparaison = new compBioSec();
		    
		    
           double pourcentage= comparaison.pourcentageHaming(comparaison.Hamming(biocodeClient,biocodeServeur,20), 20);
  
    	   System.out.println("pourcentage -------"+pourcentage);
   	    
    	   //0.9 correspond au seuil  
    	   if(pourcentage >= 0.9){
   		
    		   System.out.println("biocode ok");
   		
    	   }else if(pourcentage < 0.9){
    		   
    		   System.out.println("biocode no");
   		  
    	   }else{

    		   System.out.println("biocode prob !!!!");
   		}
		  
		  
		  
		  
		  
	}

}
