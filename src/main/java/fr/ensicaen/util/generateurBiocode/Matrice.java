package fr.ensicaen.util.generateurBiocode;


/** 
* @author Yassir Mohamed Bouhaddaoui  yassir.bouhaddaoui@gmail.com
*/

public class Matrice {


	
	
	private double vecteurs[][];
	private int col;
	private int row;
	
	
	
	 /** 
     * Constructeur d'une matrice vide specifiée par ses dimensions
     * 
     * @param row nombre de lignes
     * @param col nombre de colonnes
     */
	public Matrice(int row,int col){

		this.vecteurs = new double[row][col];
		this.row=row;
		this.col= col;
		
		for(int i =0 ; i<row ; i++){
			
			for(int j=0 ; j<col ; j++){
				vecteurs[i][j] = 0;
			}
		}
	}
	
	
  

    /** 
     * Constructeur d'une matrice à partir d'une existante.
     * @param m matrice à copier
     */
    public Matrice( Matrice m ) {
	
	    this.row = m.getRow();
		this.col = m.getCol();
		vecteurs = new double[this.row][this.col];
		
		for ( int i = 0; i < row; i++ ) {
		    for ( int j = 0; j < col; j++ ) {
			vecteurs[i][j] = m.getVecteurs()[i][j];
		    }
		}

	}
    

    /**
     * Constructeur d'une matrice à partir d'un tableau de double de dimension
     * 2. Le tableau doit être bien rectangulaire.
     * @param t tableau de nombres
     */
    public Matrice( double t[][] ) {
	this.row = t.length;
	this.col = t[0].length;
	this.vecteurs = new double[row][col];
	int i = 0;
	int j = 0;
	
	try {
	    for ( i = 0; i < row; i++ ) {
		for ( j = 0; j < col; j++ ) {
		    vecteurs[i][j] = t[i][j];
		} 		
	    }
	

	} catch ( ArrayIndexOutOfBoundsException e ) {
	    System.out.println( "Erreur dans l'initialisation de matrice:" );
	    System.out.println( "l'élément (" + i + "," + j +
				") n'a pas pu être trouvé." );
	    return;
	}

    }


	public double[][] getVecteurs() {
		return vecteurs;
	}




	public void setVecteurs(double[][] vecteurs) {
		this.vecteurs = vecteurs;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public int getRow() {
		return row;
	}




	public void setRow(int row) {
		this.row = row;
	}
	
	

	


	
}
