package es.ucm.fdi.tfg.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.ucm.fdi.tfg.app.entity.Recommendation;
import es.ucm.fdi.tfg.app.entity.User;
import es.ucm.fdi.tfg.app.repository.RecommendationRepository;
import es.ucm.fdi.tfg.app.repository.UserRepository;
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


    @Scheduled(fixedDelay = 1000 * 60 * 60 * 5)
    public void test() throws TasteException, IOException {
        System.out.println("Starting recommendation");
        
        //DataModel model = new FileDataModel(new File(servletContext.getRealPath("/WEB-INF/csv/rating.csv")));
        DataModel model = MahoutDataModel.getDataModelFromPostreSQL(url, user, password);
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        neighborhood = new NearestNUserNeighborhood(25, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        
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