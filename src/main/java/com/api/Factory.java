package com.api;

import com.DAO.*;

/**
 * Created by dima on 24.01.17.
 */
public class Factory {

    private static Factory instance = new Factory();

    private static ColorDAO colorDAO = null;
    private static EngineDAO engineDAO = null;
    private static KindOfBodyDAO kindOfBody = null;
    private static ModelNameDAO modelNameDAO = null;
    private static TransmissionDAO transmissionDAO = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }



    public ColorDAO getColorDAO(){
        if (colorDAO == null){
            colorDAO = new ColorDAO();
        }
        return colorDAO;
    }

    public EngineDAO getEngineDAO(){
        if (engineDAO == null){
            engineDAO = new EngineDAO();
        }
        return  engineDAO;
    }

    public KindOfBodyDAO getKindOfBody(){
        if (kindOfBody == null){
            kindOfBody = new KindOfBodyDAO();
        }
        return kindOfBody;
    }

    public ModelNameDAO getModelNameDAO(){
        if (modelNameDAO == null){
            modelNameDAO = new ModelNameDAO();
        }
        return modelNameDAO;
    }

    public TransmissionDAO getTransmissionDAO(){
        if (transmissionDAO == null){
            transmissionDAO = new TransmissionDAO();
        }
        return  transmissionDAO;
    }




}
