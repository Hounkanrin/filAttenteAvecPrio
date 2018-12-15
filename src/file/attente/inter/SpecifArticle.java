package file.attente.inter;

import file.attente.classes.Article;

public interface SpecifArticle {
	
	// désignation de l'article	
		public String getDesignation();
		
	//quantité en stock
		public int getQuantite(); 
		
	// prix H T 
		public double getPrixHT(); 
		
	// prix TTC = prix HT * taux de TVA (1.196)
		public double getPrixTTC();
	
	// augmenter le stock de la quantité q 
		public void ajouter(int q); 
	
	// réduire le stock de la qunatité q 
		public void retirer(int q); //throws stockInsuffissantException;
	
	//taux de TVA en vigueur pour l'article 
		
	public double getTauxTVA();

	int compareTo(Article o);

	//String getNumeroISBN();
}
