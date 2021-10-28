package com.itgarden.service;

import java.util.ArrayList;

public class DataListService extends ArrayList<String> {

    public boolean save(String data) {
        if(data.isBlank() || data.isEmpty()) {
            return false;
        }
        return add(data);
    }

    public String view (int index) {
        return get(index).toUpperCase();
    }

}
