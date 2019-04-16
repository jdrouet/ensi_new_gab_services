package fr.ensicaen.util.generateurBiocode;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Template {
        
        Matrice temp;


/** 
 * lire le FingerCode à partir du fichier test 
 * @param fichier : nom de fichier
 * */
        
public String lireFileTemplate(String fichier){
                
                String ligne="";
                
                
                try{
                        InputStream ips=new FileInputStream(fichier); 
                        InputStreamReader ipsr=new InputStreamReader(ips);
                        BufferedReader br=new BufferedReader(ipsr);
                        ligne=br.readLine(); 
                        br.close(); 
                }                
                catch (Exception e){
                        System.out.println(e.toString());
                }
               // System.out.println(ligne);
                return ligne;
        }
        
 


/** 
 * retourne le nombre de colonnes
 * @param charMat : la chaine de caractere retournée par lireFileTemplate()
 * */

public int ColCount(String charMat){
        
    int counter=0;
        StringTokenizer st = new StringTokenizer(charMat,"	[],");
        while (st.hasMoreElements()) {
                
                st.nextElement(); 
                counter++;
        }

       // System.out.println("nubmer of column: "+counter);
        
        return counter;
        
}



/** 
 * Conversion d'une string en une matrice 1 ligne  n colonnes 
 * @param charMat : la chaine retournée par lireFileTemplate()
 * */

public Matrice getKeyFromFile(String charMat){
        
         int countcol = ColCount(charMat);
         temp = new Matrice(1,countcol);
         int i = 0;
        StringTokenizer st = new StringTokenizer(charMat,"	[],");
        while (st.hasMoreElements()) {
                
                String is= (String) st.nextElement();
        temp.getVecteurs()[0][i]= Double.parseDouble(is);
                 i++;
        }
        
        return temp;
}



public Matrice getTemp() {
        return temp;
}



public void setTemp(Matrice temp) {
        this.temp = temp;
}

}
