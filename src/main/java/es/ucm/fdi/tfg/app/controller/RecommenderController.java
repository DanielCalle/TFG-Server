package es.ucm.fdi.tfg.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.tfg.app.MahoutDataModel;

@Controller
@RequestMapping("/recommendations")
public class RecommenderController {
    
    @Autowired
    ServletContext servletContext;
    
    @GetMapping("/{id}")
    @ResponseBody
    public List<String> recommendUserFilms(@PathVariable int id) throws IOException, TasteException {
        //DataModel model = new FileDataModel(new File(servletContext.getRealPath("/WEB-INF/csv/rating.csv")));
        DataModel model = MahoutDataModel.getDataModelFromPostreSQL();
        //DataModel model = new PostgreSQLJDBCDataModel();
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        neighborhood = new NearestNUserNeighborhood(25, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
    
        List<String> list = new ArrayList<String>();
        List<RecommendedItem> recommendations = recommender.recommend(id, 3);
        System.out.println("recom");
        for (RecommendedItem recommendation : recommendations) {
            list.add(recommendation.toString());
        }
        return list;
    }

}