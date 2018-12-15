package javamain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import file.attente.classes.ArticlePrio;
import file.attente.classes.LivrePrio;
import file.attente.classes.SalleDAttenteAvecPrio;
import file.attente.inter.SpecifArticlePrio;
public class TestPrio
{
	
	static SalleDAttenteAvecPrio<SpecifArticlePrio> file ;
	static LinkedList<SpecifArticlePrio> listeArticles ;
	static boolean termine ;
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		try{
		    System.out.println ("Taille de la file d'attente : ") ;
		    int taille = Integer.parseInt(in.readLine());
		    System.out.println ("Priorité maximum de la file d'attente : ") ;
		    int prio = Integer.parseInt(in.readLine());
		    boolean saisieOK = (taille > 0) & (prio > 1) ;
		    while (!saisieOK)
		    {
		        System.out.println ("Valeurs incorrectes, recommencez") ;
		        taille = Integer.parseInt(in.readLine());
		        prio = Integer.parseInt(in.readLine());
		        saisieOK = (taille > 0) & (prio > 1) ;
		    }
		    file = new SalleDAttenteAvecPrio<SpecifArticlePrio>(taille, prio) ;
		    listeArticles = new LinkedList<SpecifArticlePrio>() ;
		    for (termine = false ; !termine ; menu() ) ;
		}
		catch(IOException e){
		    System.err.println("Erreur de saisie !!!");
		    System.err.println("Sortie...");
		    System.exit(1);
		}
		catch(NumberFormatException e){
		    System.err.println("Ce n'est pas un nombre entier !!!");
		    System.err.println("Sortie...");
		    System.exit(1);
		}
	}
	
	private static void menu() throws NumberFormatException, IOException
	{
	    System.out.println();
	    System.out.println ("1 : ajouter un article ou livre") ;
		System.out.println ("2 : faire sortir un article ou livre") ;
		System.out.println ("3 : modifier le stock d'un article ou livre") ;
		System.out.println ("4 : mettre à jour les priorités de la file d'attente") ;
        System.out.println ("0 : quitter") ;
        System.out.println ("----> votre choix : ") ;
        int choix = Integer.parseInt(in.readLine());
        switch (choix)
		{
           case 1 : ajout(); break;
           case 2 : retrait(); break;
           case 3 : modifierStock(); break;
           case 4 : majFile(); break;
           case 0 : termine = true ; break;
           default:
           		System.out.println ("Mauvais choix !") ;
           		menu() ;
		}
	}
	
	private static void ajout() throws IOException
	{
		SpecifArticlePrio art ;
		System.out.println ("\nEntrer la désignation de l'article ou livre : ") ;
		String des = in.readLine();
		System.out.println ("Entrer le stock de l'article ou livre : ") ;
		int stock = Integer.parseInt(in.readLine());
		System.out.println ("Entrer le n°ISBN, s'il s'agit d'un livre : ") ;
		String isbn = in.readLine();
		if (isbn.length() == 0)
		{
			// C'est un simple article
			art = new ArticlePrio(des, stock, 0.0) ;
		}
		else
		{
			// C'est un livre
			System.out.println ("Entrer le nombre de pages : ") ;
			int nbp = Integer.parseInt(in.readLine());
			art = new LivrePrio(des, stock, 0.0, nbp, isbn) ;
		}
		file.entrer(art);
		listeArticles.addLast(art);
		System.out.println ("Ajout de : " + art.toString()) ;
	}
	
	private static void retrait()
	{
        if (file.estVide())
        {
            System.out.println();
        	System.out.println ("Impossible : file vide !") ;
        }
        else
        {
            System.out.println();
        	System.out.println ("Sortie de : " + file.getProchain().toString()) ;
            listeArticles.remove (file.getProchain()) ;
            file.sortir() ;
            System.out.println ("Il reste " + file.getNbClients() + " articles ? attendre") ;
        }
	}
	
	private static void modifierStock() throws IOException
	{
	    System.out.println();
		System.out.println ("Désignation de l'article ?") ;
		String des = in.readLine();
		int i ;
		for (i = 0 ;
				i < listeArticles.size()
				   && ! ((ArticlePrio) listeArticles.get(i)).getDesignation().equals(des) ;
				i++) ;
		if (i == listeArticles.size())
		{
			System.out.println ("Article inexistant, recommencez") ;
		}
		else
		{
			System.out.println ("Entrer une valeur (positive ou négative) à ajouter au stock : ") ;
			int delta = Integer.parseInt(in.readLine());
			ArticlePrio art = (ArticlePrio) listeArticles.get(i) ;
			if (delta < 0)
			{
				art.retirer(-delta) ;
			}
			else
			{
				art.ajouter(delta) ;
			}
		}
	}

	private static void majFile()
	{
		file.reorganiser() ;
	}
}
