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
/*
        // add some colors to base
        List <Color> arrayColor = new ArrayList<Color>();
        arrayColor.add(new Color("black",true));
        arrayColor.add(new Color ("blue",true));
        arrayColor.add(new Color("red",true));
        arrayColor.add(new Color ("yellow",false));
        arrayColor.add(new Color("black",false));

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
        arrayEngine.add(new Engine("1.6L Ti-VCT I-4 Engine",120));
        arrayEngine.add(new Engine("1.0L EcoBoost I-3 Engine",123));
        arrayEngine.add(new Engine("1.6L 16-valve Ti-VCT Turbocharged Direct Injection EcoBoost I-4",197));
        arrayEngine.add(new Engine("2.0L Ti-VCT I-4 Flex Fuel",160));
        arrayEngine.add(new Engine("Electric"));

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
        arrayKindOfBody.add(new KindOfBody("sedan"));
        arrayKindOfBody.add(new KindOfBody("hatch"));
        arrayKindOfBody.add(new KindOfBody("suv"));

        for(KindOfBody kindOfBody:arrayKindOfBody){
            apiDb.create(kindOfBody);
        }

        // add some model name to base
        List<ModelName>arrayModel = new ArrayList<ModelName>();
        arrayModel.add(new ModelName("Focus","fordfocus.png"));
        arrayModel.add(new ModelName("Fiesta","fordfiesta.png"));
        arrayModel.add(new ModelName("Mondeo","fordmondeo.png"));
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

        arrayCarParametrs.add(new CarParametrs(arrayModel.get(1),arrayKindOfBody.get(0), arrayEngine.get(1),arrayTransmission.get(0),arrayColorSet.get(0)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(2),arrayKindOfBody.get(0),arrayEngine.get(2),arrayTransmission.get(2),arrayColorSet.get(2)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(2),arrayKindOfBody.get(0),arrayEngine.get(3),arrayTransmission.get(3),arrayColorSet.get(3)));

        arrayCarParametrs.add(new CarParametrs(arrayModel.get(3),arrayKindOfBody.get(2), arrayEngine.get(1),arrayTransmission.get(0),arrayColorSet.get(1)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(3),arrayKindOfBody.get(2),arrayEngine.get(2),arrayTransmission.get(2),arrayColorSet.get(2)));
        arrayCarParametrs.add(new CarParametrs(arrayModel.get(3),arrayKindOfBody.get(2),arrayEngine.get(3),arrayTransmission.get(3),arrayColorSet.get(3)));

        for(CarParametrs carParametrs:arrayCarParametrs){
            apiDb.create(carParametrs);
        }

        User admin = new User("dima", "123456", Role.ADMINISTRATOR);
        User salesMan = new User ("alex","23456",Role.STAFF);
        User someUser = new User ("someUser", "2343");
        apiDb.create(admin);
        apiDb.create(salesMan);
        apiDb.create(someUser);


        List<CarParametrs> carParametrsList = Factory.getInstance().getCarParametrsDAO().getByModel(1l);
        System.out.println(carParametrsList);



        String modelName = "Explorer";

        String engineReq ="Select e "+
                "From CarParametrs c "+
                "INNER JOIN c.engine e " +
                "Where c.modelName.modelName = :model ";



        Session session = null;
        List objects  = null;

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(engineReq)
                .setString("model",modelName);
        objects = query.list();

        System.out.println(objects);*/
    }
}