package file.attente.inter;

public interface SalleDAttente<TC>
{
	public int getCapacite() ;			// Capacit� de la salle
	public int getNbClients() ;			// Nombre de clients dans la salle
	public boolean estVide() ;			// Salle vide ?
	public boolean estPleine() ;		// Salle pleine ?
    public TC getProchain() ;			// Prochain client � servir
    public void sortir() ;				// Sortie de la salle du prochain client � servir
    public void entrer(TC o) ;			// Entr�e dans la salle du client c
}
