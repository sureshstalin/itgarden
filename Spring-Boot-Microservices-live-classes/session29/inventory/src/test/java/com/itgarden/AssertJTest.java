package com.itgarden;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {

    @Test
    public void simpleTest() {
        String expected = "Suresh";
        String actual = "Suresh";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void checkList() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(20, 40, 50));//, null));
        assertThat(numbers).isNotNull().isNotEmpty().contains(40);
        assertThat(numbers).hasSize(3);
        assertThat(numbers).contains(50, 20);
        assertThat(numbers).containsAll(Arrays.asList(20, 40, 50));
        assertThat(numbers).allMatch(n -> n > 10);

    }
}
