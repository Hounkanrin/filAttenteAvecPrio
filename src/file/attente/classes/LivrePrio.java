package file.attente.classes;

import file.attente.inter.SpecifArticlePrio;
import file.attente.inter.SpecifLivre;

public class LivrePrio extends Livre implements SpecifLivre, SpecifArticlePrio {

	public LivrePrio(String des, int q, double prix, int nbp, String isbn) {
		super(des, q, prix, nbp, isbn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getPrio() {
		int stockMin = (int) (Article.stockMinimal + (Article.stockMinimal *0.5));
		return this.getQuantite() >= stockMin ? 0 : stockMin - this.getQuantite();
	}

}
