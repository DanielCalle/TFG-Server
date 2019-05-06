package es.ucm.fdi.tfg.app;

import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLBooleanPrefJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class MahoutDataModel {

    public static DataModel getDataModelFromPostreSQL(String url, String user, String password) {
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