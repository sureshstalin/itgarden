package com.itgarden.restweb.service;

import com.itgarden.restweb.handler.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.es.CalendarData_es_HN;

@Service
public class SetterInjectionService {

    private DataHandler dataHandler;

    public DataHandler getDataHandler() {
        return dataHandler;
    }

    @Autowired
    public void setDataHandler(DataHandler dataHandler) {
        System.out.println("Datahandler initializing");
        this.dataHandler = dataHandler;
        System.out.println("Datahandler initialized");
    }
}
