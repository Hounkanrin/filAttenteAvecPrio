package file.attente.classes;

import file.attente.inter.SpecifLivre;

public class Livre extends Article implements SpecifLivre
{
   private int nbPages ;
   private String numISBN ;
   
   public int getNombrePages() { return nbPages ; }
   public String getNumeroISBN() { return numISBN ; }
   
   public Livre (String des, int q, double prix, int nbp, String isbn)
   {
   	   super (des, q, prix) ;
   	   nbPages = nbp ;
   	   numISBN = isbn ;
   }
   
   public String toString()
   {
   	  return
	  	getDesignation()
		+ ", ISBN : " + getNumeroISBN()
	    + " (" + getNombrePages() + " pages)";
   }
}

