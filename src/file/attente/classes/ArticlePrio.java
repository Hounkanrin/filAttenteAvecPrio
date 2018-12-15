package file.attente.classes;

import file.attente.inter.SpecifArticle;
import file.attente.inter.SpecifArticlePrio;

public class ArticlePrio extends Article implements SpecifArticle,SpecifArticlePrio {

	public ArticlePrio(String des, int q, double prix) {
		super(des, q, prix);
	}

	@Override
	public int getPrio() {
		return this.getQuantite() >= Article.stockMinimal ? 0 : Article.stockMinimal - this.getQuantite();
	}

}
