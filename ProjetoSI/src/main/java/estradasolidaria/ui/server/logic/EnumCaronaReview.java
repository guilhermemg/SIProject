package estradasolidaria.ui.server.logic;

/**
 * Classe representante dos reviews de carona,
 * eh usada quando se lida com reviews.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public enum EnumCaronaReview {
	FALTOU("faltou"), NAO_FALTOU("não faltou"),
	SEGURA_E_TRANQUILA("segura e tranquila"), NAO_FUNCIONOU("não funcionou");
	
	private String review;
	private EnumCaronaReview(String review) {
		this.review = review;
	}
	
	public String getReview(){
		return review;
	}
}
