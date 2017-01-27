package com.test;


import com.api.ApiDb;
import com.carEntity.*;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 09.12.16.
 */
public class TestClass {
    public static void main(String[] args) throws SQLException, IOException {

        ApiDb apiDb = ApiDb.getInstance();
        ObjectMapper objectMapper = new ObjectMapper();
        String baseJson = "";
        String create = "create/";
        String delete = "delete/";
        String requestText = "";

        // add some colors to base
        List <Color> arrayColor = new ArrayList<Color>();
        arrayColor.add(new Color("black",false));
        arrayColor.add(new Color ("blue",true));
        arrayColor.add(new Color("red",true));

        for(Color color: arrayColor){
            baseJson = objectMapper.writeValueAsString(color);
            requestText = create + baseJson;
            apiDb.makeReq(requestText);
        }
        /*
        // add some engine to base
        List<Engine>arrayEngine = new ArrayList<Engine>();
        arrayEngine.add(new Engine("1.6L Ti-VCT I-4 Engine",120));
        arrayEngine.add(new Engine("1.0L EcoBoost I-3 Engine",123));
        arrayEngine.add(new Engine("1.6L 16-valve Ti-VCT Turbocharged Direct Injection EcoBoost I-4",197));
        arrayEngine.add(new Engine("2.0L Ti-VCT I-4 Flex Fuel",160));
        arrayEngine.add(new Engine("1.0L EcoBoost I-3 Engine",123));
        arrayEngine.add(new Engine("Electric"));

        for(Engine engine:arrayEngine){
            baseJson = objectMapper.writeValueAsString(engine);
            requestText = create + baseJson;
            apiDb.makeReq(requestText);
        }


        // add some transmission to base
        List<Transmission>arrayTransmission = new ArrayList<Transmission>();
        arrayTransmission.add(new Transmission("5-speed manual",5));
        arrayTransmission.add(new Transmission("6-speed manual",6));
        arrayTransmission.add(new Transmission("PowerShift 6-speed automatic",6));
        arrayTransmission.add(new Transmission("6-speed automatic with SelectShift",6));
        arrayTransmission.add(new Transmission("6-speed PowerShift automatic",6));

        for(Transmission transmission:arrayTransmission){
            baseJson = objectMapper.writeValueAsString(transmission);
            requestText = create + baseJson;
            apiDb.makeReq(requestText);
        }

        // add some type of body to base
        List<KindOfBody> arrayKindOfBody = new ArrayList<KindOfBody>();
        arrayKindOfBody.add(new KindOfBody("sedan"));
        arrayKindOfBody.add(new KindOfBody("hatch"));
        arrayKindOfBody.add(new KindOfBody("suv"));

        for(KindOfBody kindOfBody:arrayKindOfBody){
            baseJson = objectMapper.writeValueAsString(kindOfBody);
            requestText = create + baseJson;
            apiDb.makeReq(requestText);
        }

        // add some model name to base
        List<ModelName>arrayModel = new ArrayList<ModelName>();
        arrayModel.add(new ModelName("focus"));
        arrayModel.add(new ModelName("fiest"));
        arrayModel.add(new ModelName("mustang"));
        arrayModel.add(new ModelName("explorer"));

        for(ModelName modelName:arrayModel){
            baseJson = objectMapper.writeValueAsString(modelName);
            requestText = create + baseJson;
            apiDb.makeReq(requestText);
        }

        /*
        //check getAll and delete - work okey
        List<Color> colors = new ArrayList<>(apiDb.getFactory().getColorDAO().getAll());

        for(Color color:colors){
            System.out.println(color);
            apiDb.getFactory().getColorDAO().delete(color);
        }

        List<Engine> engines = new ArrayList<>(apiDb.getFactory().getEngineDAO().getAll());

        for(Engine engine:engines){
            System.out.println(engine);
            apiDb.getFactory().getEngineDAO().delete(engine);
        }*/


        /*ColorSet colorSet = new ColorSet();
        colorSet.addColor(arrayColor.get(0));
        colorSet.addColor(arrayColor.get(1));

        CarParametrs carParametrs = new CarParametrs(arrayEngine.get(1),arrayKindOfBody.get(1),arrayModel.get(1),arrayTransmission.get(1),colorSet);
        baseJson = objectMapper.writeValueAsString(carParametrs);
        requestText = create + baseJson;
        apiDb.makeReq(requestText);*/
    }
}