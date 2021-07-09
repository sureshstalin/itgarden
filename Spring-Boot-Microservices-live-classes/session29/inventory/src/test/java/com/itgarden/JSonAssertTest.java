package com.itgarden;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JSonAssertTest {


    public String getJSon(String jsonFile) {
        JsonReader jsonReader = new JsonReader();
        return jsonReader.readJson(jsonFile);
    }
    @Test
    public void jsonAssertTest1() throws Exception {
        String result = getJSon("product1.json");
        JSONAssert.assertEquals("[{id:1},{id:2},{id:3}]", result, false); // Pass
    }

    @Test
    public void jsonAssertTest2() throws Exception {
        String result = "[1,2,3,5,4]";
        JSONAssert.assertEquals("[1,2,3,4,5]", result, true); // Pass
    }

    @Test
    public void jsonAssertTest3() throws Exception {
        String result = getJSon("product3.json");
        JSONAssert.assertEquals("[{product:[{price:200},{price:300},{price:100}]}]", result, true); // Pass
    }
}
