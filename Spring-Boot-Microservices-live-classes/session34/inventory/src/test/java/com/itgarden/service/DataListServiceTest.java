package com.itgarden.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DataListServiceTest {

    DataListService dataListService = mock(DataListService.class);

    @Test
    public void addParamTest() {
        when(dataListService.save("Joe")).thenReturn(true);
        assertEquals(true, dataListService.save("Joe"));
    }

    @Test
    public void addParamNegativeTest() {
        when(dataListService.save(null)).thenReturn(false);
        assertEquals(false, dataListService.add(null));
    }

    @Test
    public void addParamAnyStringTest() {
        when(dataListService.save(anyString())).thenReturn(true);
        assertEquals(true, dataListService.save("joe"));
    }

    @Test
    public void paramWithReturnValue() {
        when(dataListService.view(0)).thenReturn("ITGarden");
        assertEquals("ITGarden", dataListService.view(0));
    }

    @Test
    public void paramWithGenericReturnValue() {
        when(dataListService.view(anyInt())).thenReturn("ITGarden");
        assertEquals("ITGarden", dataListService.view(0));
        assertEquals("ITGarden", dataListService.view(1));
    }

    @Test
    public void verifyTest() {
        dataListService.save("ram");
        dataListService.save("Mani");
        verify(dataListService, atLeastOnce()).save(anyString());
        verify(dataListService, times(2)).save(anyString());
        verify(dataListService, never()).save("suresh");
    }

    @Test
    public void argumentCaptureTest() {
        dataListService.save("Suresh");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(dataListService).save(argumentCaptor.capture());
        assertEquals("Suresh", argumentCaptor.getValue());
    }

    @Test
    public void argumentMultipleCaptureTest() {
        dataListService.add("Joe");
        dataListService.add("Peter");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(dataListService, times(2)).add(argumentCaptor.capture());
        List<String> allValues = argumentCaptor.getAllValues();
        assertEquals("Joe", allValues.get(0));
        assertEquals("Peter", allValues.get(1));
    }

    @Test
    public void spyTest() {
        dataListService.view(0);
        DataListService dataListService1 = spy(DataListService.class);
        int size = dataListService1.size();
        System.out.println("Size  " + size);
        dataListService1.save("Java");
        dataListService1.save("mysql");
        size = dataListService1.size();
        System.out.println("Size " + size);
        System.out.println(dataListService1.view(1));
        // mocking here
        when(dataListService1.size()).thenReturn(10);
        size = dataListService1.size();
        System.out.println("After mocking  " + size);
        when(dataListService1.view(0)).thenReturn("suresh");
        System.out.println(dataListService1.view(0));
    }
}
