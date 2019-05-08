package es.ucm.fdi.tfg.app.controller;

import java.util.List;

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
import es.ucm.fdi.tfg.app.sa.SARecommendation;
import es.ucm.fdi.tfg.app.transfer.TRecommendation;

@Controller
@RequestMapping("/recommendations")
public class RecommenderController {

    @Autowired
    SARecommendation saRecommendation = SAFactory.getInstance().generateSARecommendation();

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
    public ResponseEntity<List<TRecommendation>> recommend(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(saRecommendation.findByUserId(id));
    }

}