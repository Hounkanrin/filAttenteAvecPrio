package file.attente.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import file.attente.inter.AvecPrio;
import file.attente.inter.SalleDAttente;

public class SalleDAttenteAvecPrioTest {
  private SalleDAttente<AvecPrio> salle;
  private SalleDAttentePAPS<ArticlePrio> hall;
  
  private SalleDAttentePAPS<Article> file; 
  private SalleDAttente<ArticlePrio> fil; 
  
	@Before
	public void setup() {
		
		salle = new SalleDAttenteAvecPrio<AvecPrio>(20, 5);
		hall = new SalleDAttentePAPS<ArticlePrio>(10);
		
		fil= new SalleDAttenteAvecPrio<ArticlePrio>(4, 3);
		file = new SalleDAttentePAPS<Article>(5);
		
	}
	
	
	// question 09 
	/* afin de vérifier le fonctionnement de ce test on a eu un échec pour test min ci dessous et le bon fonctionnement de tesrPrioMinimal
	 * 
	 */
	@Test
	public void testPrioMinimal() {
		LivrePrio bouquin = new LivrePrio("C stock 0", 0, 23.3, 123, "fhgjh") ;
		salle.entrer (new LivrePrio("A stock 5", 5, 23.3, 123, "huijb")) ;
		assertTrue(salle.getNbClients() == 1);
		salle.entrer (new LivrePrio("B stock 5", 5, 23.3, 123, "isbn")) ;
		assertTrue(salle.getNbClients() == 2);
		salle.entrer (bouquin) ;
		assertFalse(salle.getNbClients() == 4);
		
		salle.entrer (new LivrePrio("D stock 7", 7, 23.3, 123, "bfhgh")) ;
		bouquin= (LivrePrio) salle.getProchain();
		assertTrue("isbn=huijb",bouquin.getNumeroISBN()== "huijb");
		salle.sortir();
		assertTrue(salle.getNbClients() == 3);
		
		ArticlePrio  client = new ArticlePrio("doc", 10, 15.0);
		hall.entrer(client);
		
		hall.entrer(new ArticlePrio("HUU", 15, 20.0));
		
		hall.entrer(new ArticlePrio("fll", 15, 23.0));
		assertFalse(hall.getFirst().getDesignation()=="fll");
		hall.sortir();
		
	}
	// question 10
	@Test 
	public void  testMin() {
		ArticlePrio art = new ArticlePrio("F stock 0", 2, 15);
		fil.entrer(art);
		assertTrue (fil.getNbClients()==1);
		
		ArticlePrio art2 = new ArticlePrio("B stock 0", 2, 15);
		fil.entrer(art2);
		assertTrue (fil.getNbClients()==2);
		
		
		Article art1 = new Article ("F stock 0", 2, 15);
		file.entrer(art1);
		assertTrue (file.getNbClients()==1);
		
		ArticlePrio art3 = new ArticlePrio("B stock 0", 2, 15);
		file.entrer(art3);
		assertTrue (file.getNbClients()==2);
		
		fil.getProchain();
		//assertTrue("",art.getDesignation() == "B stock 0");
		//assertTrue("le prochain element a sortir est F", fil.getProchain()="F stock 0");
		assertEquals("le prochain element a sortir est F", "F stock 0", fil.getProchain().getDesignation());
		
		file.getProchain();
		assertEquals("le prochain element a sortir est B", "B stock 0", file.getProchain().getDesignation());
		
	}
}
