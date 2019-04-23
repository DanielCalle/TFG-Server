package es.ucm.fdi.tfg.app.sa;

import es.ucm.fdi.tfg.app.entity.RatingFilm;
import es.ucm.fdi.tfg.app.transfer.TRatingFilm;

public interface SARatingFilm {

    public TRatingFilm read(String user, String film);
    
    public TRatingFilm save(TRatingFilm tRatingFilm);

}
