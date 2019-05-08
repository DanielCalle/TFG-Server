package es.ucm.fdi.tfg.app.sa;

import java.util.List;

import es.ucm.fdi.tfg.app.transfer.TRecommendation;

public interface SARecommendation {

	public TRecommendation create(TRecommendation tRecommendation);
	
	public List<TRecommendation> readAll();

	public List<TRecommendation> findByUserId(long id);

	public void deleteAll();

	public TRecommendation save(TRecommendation tRecommendation);

}
