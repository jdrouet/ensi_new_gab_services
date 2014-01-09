package fr.ensicaen.util.generateurBiocode;




/** 
* @author Yassir Mohamed Bouhaddaoui  yassir.bouhaddaoui@gmail.com
*/

public class BioGenerator {


		
		private int biocode[][];
		private Matrice B;
		
		
		
		public BioGenerator(Matrice b){
			
			this.B=b;
			biocode= new int[1][b.getCol()];
		
		}

		public BioGenerator(){

			
		}	
		
		public void calculebiocode(){
		
			
			for (int i =0;i<B.getCol();i++){
				if(B.getVecteurs()[0][i] > 1){
					biocode[0][i]=1;
				}else{
					
					biocode[0][i]=0;
				}
			}
			
		}

	
		public String biocodegenServeur(RandomVector randomV,Matrice finger ){
		   
	         GramShmitt a = new GramShmitt();
	    

		     Matrice gram = a.calculeVecteur(randomV.getRandV());
		     Matrice bio =  a.MultiplyMatrice(finger, gram);
		     a.afficher(bio);
		     BioGenerator p = new BioGenerator(bio);
		     p.calculebiocode();
		    
		     //a.afficher(p.getBiocode());
		     return toTable(p.getBiocode(),p.getB().getCol());
	}
		
		

		public String biocodegenClient(Matrice vector, Matrice finger){

	         GramShmitt a = new GramShmitt();
	 	     
		    // a.afficher(vector);
		     Matrice gram = a.calculeVecteur(vector);
		     Matrice bio =  a.MultiplyMatrice(finger, gram);
		     a.afficher(bio);
		     BioGenerator p = new BioGenerator(bio);
		     p.calculebiocode();
		    
		    // a.afficher(p.getBiocode());
		     return toTable(p.getBiocode(),p.getB().getCol());
	}
		
		public String toTable(int [][] a,int taille){

			String c="";
		
			for(int i=0;i<taille;i++){
				c=c+a[0][i];
			}
			
			return c;
		}
		

		public int[][] getBiocode() {
			return biocode;
		}


		public void setBiocode(int[][] biocode) {
			this.biocode = biocode;
		}


		public Matrice getB() {
			return B;
		}

		

		public void setB(Matrice b) {
			B = b;
		}



	
	
}
