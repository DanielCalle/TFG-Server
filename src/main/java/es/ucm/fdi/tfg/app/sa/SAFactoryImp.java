package es.ucm.fdi.tfg.app.sa;

public class SAFactoryImp extends SAFactory {

	@Override
	public SAUser generateSAUser() {
		return new SAUserImp();
	}
	
	@Override
	public SAFilm generateSAFilm() {
		return new SAFilmImp();
	}

}
