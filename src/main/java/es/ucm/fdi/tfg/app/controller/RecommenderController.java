package es.ucm.fdi.tfg.app.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.javatuples.Pair;
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
import es.ucm.fdi.tfg.app.sa.SAPlan;
import es.ucm.fdi.tfg.app.sa.SARecommendation;
import es.ucm.fdi.tfg.app.sa.SAUser;
import es.ucm.fdi.tfg.app.transfer.TPlan;
import es.ucm.fdi.tfg.app.transfer.TRecommendation;

@Controller
@RequestMapping("/recommendations")
public class RecommenderController {

    private static final int MAX_PLAN_RECOMMENDATIONS = 3;

    @Autowired
    SARecommendation saRecommendation = SAFactory.getInstance().generateSARecommendation();

    @Autowired
    SAUser saUser = SAFactory.getInstance().generateSAUser();

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

    @GetMapping("/{id}/plans/{friendId}")
    @ResponseBody
    public ResponseEntity<List<Pair<TPlan, TRecommendation>>> recommendPlanForFriend(@PathVariable Long id,
            @PathVariable Long friendId) {
        List<TPlan> plans = saUser.getPlans(friendId);

        return ResponseEntity.status(HttpStatus.OK).body(plans.stream()
                .map(plan -> new Pair<TPlan, TRecommendation>(plan, saRecommendation.read(id, plan.getFilmId())))
                .filter(pair -> pair.getValue0() != null).sorted((a, b) -> a.getValue1().getRating() < b.getValue1().getRating() ? -1 : 1)
                .limit(MAX_PLAN_RECOMMENDATIONS)
                .collect(Collectors.toList())
        );
    }

}