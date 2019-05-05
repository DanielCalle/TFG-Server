package es.ucm.fdi.tfg.app;

import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLBooleanPrefJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class MahoutDataModel {

    public static DataModel getDataModelFromPostreSQL() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5433/films");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin");
        
        DataModel datamodel = new PostgreSQLJDBCDataModel(
            dataSource
        );
        
        return datamodel;
    }

}