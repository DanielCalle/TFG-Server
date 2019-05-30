package es.ucm.fdi.tfg.app.controller;

import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.sa.SAFactory;
import es.ucm.fdi.tfg.app.sa.SAFilm;
import es.ucm.fdi.tfg.app.sa.SAPlan;
import es.ucm.fdi.tfg.app.sa.SARecommendation;
import es.ucm.fdi.tfg.app.sa.SAUser;
import es.ucm.fdi.tfg.app.sa.SAUserFilm;
import es.ucm.fdi.tfg.app.transfer.TFilm;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TUser;
import es.ucm.fdi.tfg.app.transfer.TRecommendation;

@Controller
@RequestMapping("/recommendations")
public class RecommenderController {

    private static final int MAX_PLAN_RECOMMENDATIONS = 3;

    @Autowired
    SARecommendation saRecommendation = SAFactory.getInstance().generateSARecommendation();

    @Autowired
    SAUser saUser = SAFactory.getInstance().generateSAUser();

    @Autowired
    SAPlan saPlan = SAFactory.getInstance().generateSAPlan();

    @Autowired
    SAFilm saFilm = SAFactory.getInstance().generateSAFilm();

    @Autowired
    SAUserFilm saUserFilm = SAFactory.getInstance().generateSAUserFilm();

    @GetMapping({ "", "/" })
    @ResponseBody
    public ResponseEntity<List<TRecommendation>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(saRecommendation.readAll());
    }

    @DeleteMapping({ "", "/" })
    @ResponseBody
    public ResponseEntity<TRecommendation> deleteAll() {
        saRecommendation.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<List<TRecommendation>> recommend(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(saRecommendation.findByUserId(id));
    }

    @GetMapping("/{id}/films")
    @ResponseBody
    public ResponseEntity<List<TFilm>> findFilmsByUserId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(saRecommendation.findFilmsByUserId(id));
    }

    @GetMapping("/premiere")
    @ResponseBody
    public ResponseEntity<List<TFilm>> getPremiereFilms() {
        return ResponseEntity.status(HttpStatus.OK).body(saFilm.readAllByPremiere());
    }

    @GetMapping("/trending")
    @ResponseBody
    public ResponseEntity<List<TFilm>> getTrendingFilms() {
        return ResponseEntity.status(HttpStatus.OK).body(saUserFilm.getTredingFilms());
    }

    @GetMapping("/random")
    @ResponseBody
    public ResponseEntity<TFilm> getRandomFilm() {
        List<TFilm> films = saFilm.getRecentFilms().stream().filter(film -> film.getImageURL() != null)
                .collect(Collectors.toList());
        if (films.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            int rand = new Random().nextInt(films.size());
            return ResponseEntity.status(HttpStatus.OK).body(films.get(rand));
        }
    }

    @GetMapping("/{id}/plans/{friendId}")
    @ResponseBody
    public ResponseEntity<List<Quartet<TPlan, TRecommendation, TFilm, List<Pair<TUser, TRecommendation>>>>> recommendPlanForFriend(
            @PathVariable Long id, @PathVariable Long friendId) {
        List<TPlan> plans = saUser.getPlans(friendId);

        return ResponseEntity.status(HttpStatus.OK).body(plans.stream()
                // returns for each plan, a pair of plan and recommendation
                .map(plan -> new Pair<TPlan, TRecommendation>(plan, saRecommendation.read(id, plan.getFilmId())))
                // removes the one which has no recommendation
                .filter(pair -> pair.getValue1() != null)
                // sorting by rating (desc)
                .sorted((a, b) -> a.getValue1().getRating() < b.getValue1().getRating() ? -1 : 1)
                // limit to MAX_PLAN_RECOMMENDATIONS
                .limit(MAX_PLAN_RECOMMENDATIONS).map(pair -> {
                    // Package to Quartet<TPlan, TRecommendation, TFilm, List<TUser>>
                    List<TUser> users = saPlan.getJoinedUsers(pair.getValue0().getId());
                    users.add(0, saUser.read(friendId));
                    users.add(0, saUser.read(id));
                    Quartet<TPlan, TRecommendation, TFilm, List<Pair<TUser, TRecommendation>>> quartet = Quartet.with(
                            pair.getValue0(), pair.getValue1(), saFilm.read(pair.getValue0().getFilmId()),
                            users.stream()
                                    .map(user -> new Pair<TUser, TRecommendation>(user,
                                            saRecommendation.read(user.getId(), pair.getValue0().getFilmId())))
                                    .collect(Collectors.toList()));
                    return quartet;
                }).collect(Collectors.toList()));
    }

}