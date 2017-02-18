package com.test;


import com.api.ApiDb;
import com.api.Factory;
import com.api.HibernateUtil;
import com.authentification.userEntity.Role;
import com.authentification.userEntity.User;
import com.carEntity.*;
import org.hibernate.Query;
import org.hibernate.Session;

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

        // add some colors to base
        List <Color> arrayColor = new ArrayList<Color>();
        arrayColor.add(new Color("black",true,"blackf.png"));
        arrayColor.add(new Color ("blue",true,"bluef.png"));
        arrayColor.add(new Color("red",true,"redf.png"));
        arrayColor.add(new Color ("yellow",false,"yellow.png"));
        arrayColor.add(new Color("black",false,"blackf.png"));

        for(Color color: arrayColor){
            apiDb.create(color);
        }

        // add some colorSet to base
        ColorSet colorSetOne = new ColorSet();
        ColorSet colorSetTwo = new ColorSet();
        ColorSet colorSetThree = new ColorSet();
        ColorSet colorSetFour = new ColorSet();

        // add color to colorSet
        colorSetOne.addColor(arrayColor.get(0));
        colorSetOne.addColor(arrayColor.get(1));
        colorSetTwo.addColor(arrayColor.get(2));
        colorSetThree.addColor(arrayColor.get(0));
        colorSetFour.addColor(arrayColor.get(1));

        List<ColorSet> arrayColorSet = new ArrayList<ColorSet>();
        arrayColorSet.add(colorSetOne);
        arrayColorSet.add(colorSetTwo);
        arrayColorSet.add(colorSetThree);
        arrayColorSet.add(colorSetFour);


        for(ColorSet colorSet: arrayColorSet){
            apiDb.create(colorSet);
        }


        // add some engine to base
        List<Engine>arrayEngine = new ArrayList<Engine>();
        arrayEngine.add(new Engine("1.6L Ti-VCT I-4 Engine",120,"1.6L Ti-VCT I-4 Engine.jpeg"));
        arrayEngine.add(new Engine("1.0L EcoBoost I-3 Engine",123,"ford-ecoboost-turbo-engines.png"));
        arrayEngine.add(new Engine("1.6L 16-valve Ti-VCT Turbocharged Direct Injection EcoBoost I-4",197,"1.6L 16-valve Ti-VCT Turbocharged Direct Injection EcoBoost I-4.jpg"));
        arrayEngine.add(new Engine("2.0L Ti-VCT I-4 Flex Fuel",160,"2.0L Ti-VCT I-4 Flex Fuel.jpg"));
        arrayEngine.add(new Engine("Electric","electric.jpg"));

        for(Engine engine:arrayEngine){
            apiDb.create(engine);
        }

        // add some transmission to base
        List<Transmission>arrayTransmission = new ArrayList<Transmission>();
        arrayTransmission.add(new Transmission("5-speed manual",5));
        arrayTransmission.add(new Transmission("6-speed manual",6));
        arrayTransmission.add(new Transmission("PowerShift 6-speed automatic",6));
        arrayTransmission.add(new Transmission("6-speed automatic with SelectShift",6));
        arrayTransmission.add(new Transmission("6-speed PowerShift automatic",6));

        for(Transmission transmission:arrayTransmission){
            apiDb.create(transmission);
        }
        // add some type of body to base
        List<KindOfBody> arrayKindOfBody = new ArrayList<KindOfBody>();
        arrayKindOfBody.add(new KindOfBody("sedan","sedan.jpg"));
        arrayKindOfBody.add(new KindOfBody("hatchback","hatchback.jpg"));
        arrayKindOfBody.add(new KindOfBody("suv","suv.jpg"));

        for(KindOfBody kindOfBody:arrayKindOfBody){
            apiDb.create(kindOfBody);
        }

        // add some model name to base
        List<ModelName>arrayModel = new ArrayList<ModelName>();
        arrayModel.add(new ModelName("Focus","fordfocus.png"));
        arrayModel.add(new ModelName("Fiesta","fordfiesta.png"));
        arrayModel.add(new ModelName("Mondeo","fordmondeo.png"));
        arrayModel.add(new ModelName("Mustang","fordmustang.png"));
        arrayModel.add(new ModelName("Explorer","fordexplorer.png"));

        for(ModelName modelName:arrayModel){
            apiDb.create(modelName);
        }

        //and now we create real model to db

        //ModelName modelName,KindOfBody kindOfBody,Engine engine,Transmission transmission,ColorSet colorSet

        List<CarParametrs>arrayCarParametrs = new ArrayList<CarParametrs>();

        arrayCarParametrs.add(new CarParametrs(arrayModel.get(0),arrayKindOfBody.get(0), arrayEngine.get(0),arrayTransmission.get(0),arrayColorSet.get(0)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(0),arrayKindOfBody.get(0),arrayEngine.get(1),arrayTransmission.get(2),arrayColorSet.get(1)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(0),arrayKindOfBody.get(1),arrayEngine.get(3),arrayTransmission.get(3),arrayColorSet.get(2)));

        arrayCarParametrs.add(new CarParametrs(arrayModel.get(1),arrayKindOfBody.get(0), arrayEngine.get(0),arrayTransmission.get(0),arrayColorSet.get(0)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(1),arrayKindOfBody.get(0),arrayEngine.get(1),arrayTransmission.get(2),arrayColorSet.get(1)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(1),arrayKindOfBody.get(1),arrayEngine.get(2),arrayTransmission.get(3),arrayColorSet.get(2)));

        arrayCarParametrs.add(new CarParametrs(arrayModel.get(2),arrayKindOfBody.get(0), arrayEngine.get(1),arrayTransmission.get(0),arrayColorSet.get(0)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(2),arrayKindOfBody.get(0),arrayEngine.get(2),arrayTransmission.get(2),arrayColorSet.get(2)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(2),arrayKindOfBody.get(0),arrayEngine.get(3),arrayTransmission.get(3),arrayColorSet.get(3)));

        arrayCarParametrs.add(new CarParametrs(arrayModel.get(3),arrayKindOfBody.get(0), arrayEngine.get(1),arrayTransmission.get(0),arrayColorSet.get(0)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(3),arrayKindOfBody.get(0),arrayEngine.get(2),arrayTransmission.get(2),arrayColorSet.get(2)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(3),arrayKindOfBody.get(0),arrayEngine.get(3),arrayTransmission.get(3),arrayColorSet.get(3)));

        arrayCarParametrs.add(new CarParametrs(arrayModel.get(4),arrayKindOfBody.get(2), arrayEngine.get(1),arrayTransmission.get(0),arrayColorSet.get(1)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(4),arrayKindOfBody.get(2),arrayEngine.get(2),arrayTransmission.get(2),arrayColorSet.get(2)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(4),arrayKindOfBody.get(2),arrayEngine.get(3),arrayTransmission.get(3),arrayColorSet.get(3)));

        for(CarParametrs carParametrs:arrayCarParametrs){
            apiDb.create(carParametrs);
        }

        User admin = new User("dima", "123456", Role.ADMINISTRATOR);
        User salesMan = new User ("alex","23456",Role.STAFF);
        User someUser = new User ("someUser", "2343");
        User user1234 = new User ("1234", "1234");
        apiDb.create(admin);
        apiDb.create(salesMan);
        apiDb.create(someUser);
        apiDb.create(user1234);




         /*
        String modelName = "Explorer";
        String engineName = "1.0L EcoBoost I-3 Engine";

        String bodyReq ="Select kind "+
                "From CarParametrs c "+
                "INNER JOIN c.kindOfBody kind " +
                "Where c.modelName.modelName = :model "+
                " AND c.engine.nameOfEngine = :engine";



        Session session = null;
        List objects  = null;

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(bodyReq)
                .setString("model",modelName)
                .setString("engine",engineName);
        objects = query.list();

        System.out.println(objects);
        */
    }
}