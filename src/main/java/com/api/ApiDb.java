package com.api;


import com.carEntity.*;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;


import java.io.IOException;

/**
 * Created by dima on 07.01.17.
 */
public class ApiDb {
    private static ApiDb ourInstance = new ApiDb();
    private static ObjectMapper objectMapper;

    public static ApiDb getInstance() {
        objectMapper = new ObjectMapper();
        return ourInstance;
    }

    private ApiDb() {
    }

    public void makeReq(String req) throws IOException {
        String [] strArray = req.split("/");
        String operation = strArray[0];//it's couse the first element is name of operation
        switch (operation){
            case "create":
                //if the first token is create - pass to create the result of getParts
                create(getParts(strArray[1]));
                break;
            case "delete":
                //delete(strArray[1]);
                break;
            case "update":
                //update(strArray[2],strArray[1]);
                break;
            case "getAll":
                //мм пока не решил как вернуть массив объектов
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

    private void create(CarParts carParts) throws IOException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(carParts);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

}