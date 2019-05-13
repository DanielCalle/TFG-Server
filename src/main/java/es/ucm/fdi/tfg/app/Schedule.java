package es.ucm.fdi.tfg.app;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.ucm.fdi.tfg.app.sa.SAFactory;
import es.ucm.fdi.tfg.app.sa.SARecommendation;
import es.ucm.fdi.tfg.app.sa.SAUser;
import es.ucm.fdi.tfg.app.transfer.TRecommendation;
import es.ucm.fdi.tfg.app.transfer.TUser;

@Component
public class Schedule {

    @Autowired
    ServletContext servletContext;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

	@Autowired
	SAUser saUser = SAFactory.getInstance().generateSAUser();

	@Autowired
	SARecommendation saRecommendation = SAFactory.getInstance().generateSARecommendation();

    // Will be executed every 5h after deploy
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 5)
    public void test() throws TasteException, IOException {
        System.out.println("Starting recommendation");
        
        // this is the way of getting model from a file
        //DataModel model = new FileDataModel(new File(servletContext.getRealPath("/WEB-INF/csv/rating.csv")));
        
        // this is the way of getting model from a database (postgresql)
        DataModel model = MahoutDataModel.getDataModelFromPostreSQL(url, user, password);
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        neighborhood = new NearestNUserNeighborhood(25, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        
        // Recommends for all users and saves into database
        for (TUser user : saUser.readAll()) {
            System.out.println("Recommend for user " + user.getId());
            List<RecommendedItem> recommendations = recommender.recommend(user.getId(), 3);
            for (RecommendedItem recommendedItem : recommendations) {
                System.out.println(user.getId() + " " + recommendedItem.getItemID() + " " + recommendedItem.getValue());
                TRecommendation tRecommendation = new TRecommendation();
                tRecommendation.setUserId(user.getId());
                tRecommendation.setFilmId(recommendedItem.getItemID());
                tRecommendation.setRating(recommendedItem.getValue());
                saRecommendation.save(tRecommendation);
            }
        };
    }

}