package com.itgarden;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestTest {


    @BeforeEach
    public void init() {
        System.out.println("This is init method call before executing Test cases");
    }

    @Test
    public void listTest() {
        List<String> dataList = Arrays.asList("Suresh", "Kesavan", "Ravi");
        List<Integer> numbers = Arrays.asList(1, 3, 4, 8);
        assertThat(dataList, hasSize(3));
        assertThat(dataList, hasItems("Ravi", "Kesavan"));
        assertThat(dataList, containsInAnyOrder("Suresh", "Kesavan", "Ravi"));
//        assertThat(numbers, everyItem(greaterThan(10)));
        assertThat(numbers, everyItem(lessThan(10)));


    }

    @Test
    public void checkString() {
        assertThat("", isEmptyString());
        assertThat("Hello World", containsString("World"));
    }

    @Test
    public void checkNumber() {
        //        greaterThanOrEqualTo, lessThan, lessThanOrEqualTo
        assertThat(10, greaterThan(7));
        assertThat(10, lessThan(33));
    }

    @Test
    public void mapTest() {
        Map<String, String> countries = new HashMap<>();
        countries.put("India", "+91");
        countries.put("USA", "+1");
        assertThat(countries, hasKey("India"));
        assertThat(countries, hasEntry("USA", "+1"));
    }

}

