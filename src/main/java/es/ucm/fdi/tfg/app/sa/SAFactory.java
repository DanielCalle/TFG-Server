package es.ucm.fdi.tfg.app.sa;

public abstract class SAFactory {

	private static SAFactory instance;
	
	public synchronized static SAFactory getInstance() {
		if (instance == null)
			instance = new SAFactoryImp();
		
		return instance;
	}
	
	public abstract SAUser generateSAUser();

	public abstract SAFilm generateSAFilm();
	
}
