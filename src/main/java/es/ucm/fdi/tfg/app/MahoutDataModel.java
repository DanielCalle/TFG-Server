package es.ucm.fdi.tfg.app;

import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.postgresql.ds.PGPoolingDataSource;

public class MahoutDataModel {

    // Set connection to postgresql database
    public static DataModel getDataModelFromPostreSQL(String url, String user, String password) {
        System.out.println(url);
        System.out.println(user);
        System.out.println(password);
        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        
        DataModel datamodel = new PostgreSQLJDBCDataModel(
            dataSource, "user_film", "user_id", "film_id", "rating", null
        );
        
        return datamodel;
    }

}