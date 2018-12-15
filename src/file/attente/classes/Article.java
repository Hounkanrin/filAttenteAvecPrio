package file.attente.classes;

import file.attente.inter.SpecifArticle;

public class Article implements Comparable<Article>, SpecifArticle
{
	private final static double TAUX_TVA = 1.196 ;
	public  final static int stockMinimal = 10;

	@Override
	public double getTauxTVA()
	{ return TAUX_TVA ; }

    private String designation ;
	private int quantite ;
	private double prixHT ;

	// Constructeur à trois paramètres
	public Article (String des, int q, double prix)
	{
		assert prix > 0 ;
		assert q >= 0 ;
		assert des != null ;
		designation = des ;
		prixHT = prix ;
		quantite = q ;
	}

	// Constructeur à deux paramètres (stock nul par défaut)
	public Article (String des, double prix)
	{
		this(des, 0, prix) ;
	}

	@Override
	public String getDesignation() { return designation ; }
	@Override
	
	public double getPrixHT() { return prixHT ; }

	@Override
	public double getPrixTTC()
	{
   	  return getPrixHT() * getTauxTVA();
	}

	@Override
	public void ajouter (int q)
	{
		assert q > 0 ;
		quantite = quantite + q ;
	}

	@Override
	public void retirer (int q)
	{
		assert 0 < q & q <= getQuantite() ;
		quantite = quantite - q ;
	}

	@Override
	public int compareTo (Article autre)
	{
		return (this.getDesignation().compareTo(autre.getDesignation())) ;
		// si on remplace Comparable<Article> par Comparable<Object> :
		// return (this.toString().compareTo(autre.toString())) ;
	}

	@Override
	public String toString()
	{
		return
		  	getDesignation()
			+ ", " + getPrixTTC()
		    + " euros (" + getQuantite() + " en stock)";
	}

	@Override
	public int getQuantite() {
		
		return this.quantite;
	}

}

