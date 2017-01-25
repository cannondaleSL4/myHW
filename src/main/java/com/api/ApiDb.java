package com.api;


import com.carEntity.*;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 07.01.17.
 */
public class ApiDb {
    private static ApiDb ourInstance = new ApiDb();

    private static Factory factory = Factory.getInstance();

    private static ObjectMapper objectMapper;

    private List collectionObj;

    public static ApiDb getInstance() {
        objectMapper = new ObjectMapper();
        return ourInstance;
    }

    private ApiDb() {
    }

    public static Factory getFactory() {
        return factory;
    }

    public void makeReq(String req) throws IOException, SQLException {
        String [] strArray = req.split("/");
        String operation = strArray[0];//it's couse the first element is name of operation
        switch (operation){
            case "create":
                create(getParts(strArray[1]));
                break;
            case "delete":
                delete(getParts(strArray[1]));
                break;
            case "update":
                //update(strArray[2],strArray[1]);
                break;
            case "getAll":
                //мм пока не решил как вернуть массив объектов
                collectionObj = new ArrayList();
                //collectionObj = gettAll();
                break;
        }
    }

    private CarParts getParts(String s) throws IOException {

        JsonNode jsonNode = objectMapper.readTree(s);
        String className = jsonNode.get("@class").asText();

        switch (className){
            case "com.carEntity.Color":
                Color color = objectMapper.readValue(s,Color.class);
                return color;
            case "com.carEntity.Engine":
                Engine engine = objectMapper.readValue(s,Engine.class);
                return engine;
            case "com.carEntity.Transmission":
                Transmission transmission = objectMapper.readValue(s,Transmission.class);
                return transmission;
            case "com.carEntity.KindOfBody":
                KindOfBody kindOfBody = objectMapper.readValue(s,KindOfBody.class);
                return kindOfBody;
            case "com.carEntity.ModelName":
                ModelName modelName = objectMapper.readValue(s,ModelName.class);
                return modelName;
        }
        return null;
    }

    private void create(CarParts carParts) throws IOException, SQLException {
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
        }
    }

    private void delete(CarParts carParts) throws IOException, SQLException {
        if (carParts instanceof Color){
            factory.getColorDAO().delete((Color)carParts);
        }else if(carParts instanceof ModelName){
            factory.getModelNameDAO().delete((ModelName)carParts);
        }else if(carParts instanceof Engine){
            factory.getEngineDAO().delete((Engine)carParts);
        }else if(carParts instanceof KindOfBody){
            factory.getKindOfBody().delete((KindOfBody)carParts);
        }else if(carParts instanceof Transmission){
            factory.getTransmissionDAO().delete((Transmission)carParts);
        }
    }

}