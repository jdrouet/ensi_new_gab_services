package fr.ensicaen.util.generateurBiocode;




/** 
* @author Yassir Mohamed Bouhaddaoui  yassir.bouhaddaoui@gmail.com
*/




public class mainprog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		 /* generer template coté serveur  (enrolement)  */
		  Template tmp = new Template();
		  BioGenerator bioServeur = new BioGenerator();
		  
		  //lire l'empreinte à partire du fichier txt
		  String chem =tmp.lireFileTemplate("./src/bio/templates/I1_S1.txt");
		  
		  // mettre l'enpeinte dans une matrice
		  Matrice fingerserveur =  tmp.getKeyFromFile(chem);
		  
		  // generer le vecteur aleatoire
          RandomVector randomV = new RandomVector(fingerserveur.getCol(),200);
		  randomV.generateMatrice();
		   
		  // generation de biocode coté serveur     
		 String biocodeServeur = bioServeur.biocodegenServeur(randomV,fingerserveur);
		
		 System.out.println("S ="+biocodeServeur);
		  
		//stocker les vecteurs ( dans un fichier  --dans la BD-- ) 
		  
		  randomV.EnregistrerVector("./src/bio/randomVector.txt");
		  
		  
		  
		  
	   //coté client
		
		  
			Matrice randomVectClient;
			Matrice fingerClient;
		    Template tempclient = new Template();
		    
		    String chem2 =tempclient.lireFileTemplate("./src/bio/templates/I1_S2.txt");
		    
		    fingerClient = tempclient.getKeyFromFile(chem2);
		  
			RandomVector randV = new RandomVector(fingerClient.getCol(),200);
		
			randV.lireVector("./src/bio/randomVector.txt");
	
			randomVectClient = randV.getRandV();
			
		    
		    BioGenerator bioClient =  new BioGenerator();
			String biocodeClient = bioClient.biocodegenClient(randomVectClient, fingerClient);
			
			System.out.println("C ="+biocodeClient);
		
			
			//comparaison des deux biocodes
			
		    compBioSec comparaison = new compBioSec();
		    
		    
           double pourcentage= comparaison.pourcentageHaming(comparaison.Hamming(biocodeClient,biocodeServeur,200), 200);
  
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
