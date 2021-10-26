package com.itgarden;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class JsonReader {


    public String readJson(String jsonFile) {
        String jsonString = "";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(jsonFile).getFile());
            String absolutePath = file.getAbsolutePath();
            System.out.println(absolutePath);
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(absolutePath));
            jsonString = jsonArray.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        System.out.println(jsonReader.readJson("product1.json"));

    }
}
