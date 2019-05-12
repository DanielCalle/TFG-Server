package es.ucm.fdi.tfg.app.sa;

/**
 * Factory pattern
 */
public abstract class SAFactory {

	private static SAFactory instance;
	
	public synchronized static SAFactory getInstance() {
		if (instance == null)
			instance = new SAFactoryImp();
		
		return instance;
	}
	
	public abstract SAUser generateSAUser();

	public abstract SAFilm generateSAFilm();

	public abstract SAFriendship generateSAFriendship();

	public abstract SAPlan generateSAPlan();
	
	public abstract SAUserFilm generateSAUserFilm();
	
	public abstract SARecommendation generateSARecommendation();
	
}
