package com.itgarden;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class JsonPathTest {


    private DocumentContext getDocumentContext() {
        JsonReader jsonReader = new JsonReader();
        String jsonString = jsonReader.readJson("product1.json");
        DocumentContext documentContext = JsonPath.parse(jsonString);
        return documentContext;
    }
    @Test
    public  void jsonAssertionTest1() {
        DocumentContext documentContext = getDocumentContext();
        int jsonLength = documentContext.read("$.length()");
        assertThat(3).isEqualTo(jsonLength);
        System.out.println(jsonLength);
    }

    @Test
    public void jsonAssertionTest2() {
        DocumentContext documentContext = getDocumentContext();
        List<String> names = documentContext.read("$..name");
        System.out.println(names);
        assertThat(names).contains("Suresh","Barathan","Kishore");
    }

    @Test
    public void jsonAssertionTest3() {
        DocumentContext documentContext = getDocumentContext();
        List<Integer> ageList = documentContext.read("$[*].['age']");
        assertThat(ageList).anyMatch(integer -> integer > 10);
    }

    @Test
    public void jsonAssertionTest4() {
        DocumentContext documentContext = getDocumentContext();
        // return one product from root element
        List<LinkedHashMap<String, String>> proMaps  = documentContext.read("$..product[1]");
        System.out.println(proMaps);

    }

    @Test
    public void jsonAssertionTest5() {
        DocumentContext documentContext =getDocumentContext();
        List<LinkedHashMap<String,String>> products = documentContext.read("$..product[?(@.price>=300)]");
        System.out.println(products);
    }

}
