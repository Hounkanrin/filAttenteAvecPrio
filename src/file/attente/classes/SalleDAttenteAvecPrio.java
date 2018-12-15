package file.attente.classes;

import java.util.Iterator;

import file.attente.inter.AvecPrio;
import file.attente.inter.SalleDAttente;

public class SalleDAttenteAvecPrio<TC extends AvecPrio> implements SalleDAttente<TC>{
	
	private int capacite;
	private int maxPrio;
	private SalleDAttentePAPS<TC>[] salleDAttentePAPS;
	private int nbClts;
	
	public SalleDAttenteAvecPrio(int taille,int prio) {
		this.capacite = taille ;
		this.nbClts = 0 ;
		this.maxPrio = prio ;
		this.salleDAttentePAPS = new SalleDAttentePAPS[this.maxPrio+1] ;
		for (int i = 0; i <= this.maxPrio ; i++)
		{
			salleDAttentePAPS[i] = new SalleDAttentePAPS<TC>(getCapacite()) ;
		}
	}
	
	@Override
	public int getCapacite() {
		
		return this.capacite;
	}

	public int getMaxPrio() {
		return maxPrio;
	}

	public void setMaxPrio(int maxPrio) {
		this.maxPrio = maxPrio;
	}

	@Override
	public int getNbClients() {
		
		return this.nbClts;
	}

	@Override
	public boolean estVide() {
		
		return this.getNbClients()==0;
	}

	@Override
	public boolean estPleine() {
		
		return this.getCapacite()==this.getNbClients();
	}

	@Override
	public TC getProchain() {
		assert !estVide();
		return salleNonVidePrio().getProchain();
	}
	
	public int prioriteProchain()
	{
		assert !estVide() ;
		return getProchain().getPrio() ;
	}

	@Override
	public void sortir() {
		assert !estVide() ;
		salleNonVidePrio().sortir();
		nbClts -- ;
	   	assert !estPleine() ;
		
	}

	@Override
	public void entrer(TC o) {
		
		assert !estPleine() ;
	   	int prio = calPrio(o.getPrio()) ;
		sousSalleAvecPrio(prio).entrer(o) ;
		nbClts ++ ;
	   	assert !estVide() ;
	}
	
	/* la méthode reorganiser se base sur la priorité des sous classes prio. Getprio permet de faire la mise à jour
	 * des articles de la classe salleDAttenteAvecPrio.
	 * 
	 */
	
	public void reorganiser()
	{
		for (int prio = 0 ; !(prio <= getMaxPrio()) ; prio++)
		{
			Iterator<TC> iter = sousSalleAvecPrio(prio).iterator() ;
			while (iter.hasNext())
			{
				TC art = iter.next();
				if (calPrio(art.getPrio()) != prio)
				{
					entrer(art) ;
					iter.remove();
				}
			}
		}
	}
	
	private SalleDAttentePAPS<TC> sousSalleAvecPrio (int prio)
	{
		assert prio >= 0 ;
		if (prio > this.getMaxPrio())
		{
			prio = this.getMaxPrio() ;
		}
		return (SalleDAttentePAPS<TC>)salleDAttentePAPS[prio] ;
	}

	private SalleDAttentePAPS<TC> salleNonVidePrio()
	{
		if (getNbClients() == 0)
		{
			return null;	
		}
		else
		{
		    int i = getMaxPrio() ;
		    while (i > 0 && sousSalleAvecPrio(i).estVide())
		    {
		    	i-- ;
		    }
		    return sousSalleAvecPrio(i) ;
		}
	}
	// calcul de la nouvelle priorité.
	public int calPrio(int a)
	{
		int max = this.getMaxPrio() ;
		if (a > max) return max ;
		else return a ;
	}
}
