package com.itgarden;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTest {



    @Test
    public void equalsTest() {
        String name = "suresh";
        Assertions.assertTrue(name.equals("suresh"));
    }

    @Test
    public void equalsTestNegative() {
        String name = "suresh";
        Assertions.assertFalse(name.equals("suresh"));
    }

}
