package es.ucm.fdi.tfg.app.sa;

/**
 * Factory pattern
 */
public class SAFactoryImp extends SAFactory {

	@Override
	public SAUser generateSAUser() {
		return new SAUserImp();
	}
	
	@Override
	public SAFilm generateSAFilm() {
		return new SAFilmImp();
	}
	
	@Override
	public SAFriendship generateSAFriendship() {
		return new SAFriendshipImp();
	}
	
	@Override
	public SAPlan generateSAPlan() {
		return new SAPlanImp();
	}

	@Override
	public SAUserFilm generateSAUserFilm() {
		return new SAUserFilmImp();
	}

	@Override
	public SARecommendation generateSARecommendation() {
		return new SARecommendationImp();
	}

}
