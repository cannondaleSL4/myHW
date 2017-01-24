package com.DAO;

import com.carEntity.KindOfBody;

/**
 * Created by dima on 24.01.17.
 */
public class Factory {

    private static Factory instance = new Factory();

    private static ColorDAO colorDAO = null;
    private static EngineDAO engineDAO = null;
    private static KindOfBody kindOfBody = null;
    private static ModelNameDAO modelNameDAO = null;
    private static TransmissionDAO transmissionDAO = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    
}
