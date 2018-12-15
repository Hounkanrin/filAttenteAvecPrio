package file.attente.classes;

import java.util.LinkedList;

import file.attente.inter.SalleDAttente;

@SuppressWarnings("serial")
public class SalleDAttentePAPS<TC> extends LinkedList<TC> implements SalleDAttente<TC>
{
	protected int capacite ;

	public SalleDAttentePAPS(int capacite)
	{
		super() ;
		this.capacite = capacite ;
	}

	@Override
	public int getCapacite()			// Nombre maximum de clients
	{
		return capacite ;
	}

	@Override
	public int getNbClients()			// Nombre de clients dans la salle
	{
		return this.size() ;
	}

	@Override
	public boolean estVide()			// Salle vide ?
	{
		return this.isEmpty() ;
	}

	@Override
	public boolean estPleine()			// Salle vide ?
	{
		return getNbClients() == getCapacite() ;
	}

	@Override
    public TC getProchain()				// Prochain client à servir
    {
    	assert !estVide() ;
    	return this.getFirst() ;
    }

	@Override
    public void sortir()				// Sortie de la salle du prochain client à servir
	{
    	assert !estVide() ;
    	this.removeFirst();
	}

	@Override
    public void entrer(TC client)		// Entrée dans la salle de client
    {
    	assert !estPleine() ;
     	this.addLast (client) ;
    }
}