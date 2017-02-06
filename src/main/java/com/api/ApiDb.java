package com.api;


import com.authentification.User;
import com.carEntity.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/*
        Create = POST
        Read = GET
        Update = PUT
        Delete = DELETE
    */
public class ApiDb {

    private String [] strArray;

    private static ApiDb ourInstance = new ApiDb();
    private static Factory factory = Factory.getInstance();

    public static ApiDb getInstance() {
        return ourInstance;
    }

    private ApiDb() {
    }

    public static Factory getFactory() {
        return factory;
    }

    //@POST
    //@Path("/create")
    //@Consumes("application/json")
    public void create(Object carParts) throws IOException, SQLException {
        if (carParts instanceof Color){
            factory.getColorDAO().add((Color)carParts);
        }else if(carParts instanceof ModelName){
            factory.getModelNameDAO().add((ModelName)carParts);
        }else if(carParts instanceof Engine){
            factory.getEngineDAO().add((Engine)carParts);
        }else if(carParts instanceof KindOfBody){
            factory.getKindOfBody().add((KindOfBody)carParts);
        }else if(carParts instanceof Transmission){
            factory.getTransmissionDAO().add((Transmission)carParts);
        }else if(carParts instanceof  CarParametrs){
            factory.getCarParametrsDAO().add((CarParametrs)carParts);
        }else if(carParts instanceof  ColorSet) {
            factory.getColorSetDAO().add((ColorSet) carParts);
        }else if (carParts instanceof User){
            factory.getUserDAO().add((User) carParts);
        }
    }

    //@DELETE
    //@Path("/delete/{id}")
    //@Consumes("application/json")
    private void delete(Object carParts, Long id) throws IOException, SQLException {
        if (carParts instanceof Color){
            factory.getColorDAO().delete(id);
        }else if(carParts instanceof ModelName){
            factory.getModelNameDAO().delete(id);
        }else if(carParts instanceof Engine){
            factory.getEngineDAO().delete(id);
        }else if(carParts instanceof KindOfBody){
            factory.getKindOfBody().delete(id);
        }else if(carParts instanceof Transmission){
            factory.getTransmissionDAO().delete(id);
        }else if(carParts instanceof  CarParametrs){
            factory.getCarParametrsDAO().delete(id);
        }else if(carParts instanceof  ColorSet) {
            factory.getColorSetDAO().delete(id);
        }
    }

    //@GET
    //@Produces("application/json")
    private List<CarParts> getAll(Object carParts)throws IOException, SQLException{
        if (carParts instanceof Color){
            return factory.getColorDAO().getAll();
        }else if(carParts instanceof ModelName){
            return factory.getModelNameDAO().getAll();
        }else if(carParts instanceof Engine){
            return factory.getEngineDAO().getAll();
        }else if(carParts instanceof KindOfBody){
            return factory.getKindOfBody().getAll();
        }else if(carParts instanceof Transmission){
            return factory.getTransmissionDAO().getAll();
        }else if(carParts instanceof  CarParametrs){
            return factory.getCarParametrsDAO().getAll();
        }else {//carParts instanceof  ColorSet
            return factory.getColorSetDAO().getAll();
        }
    }
}