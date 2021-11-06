package com.user.service;

import com.user.common.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class UtilsTest {

    @InjectMocks
    Utils utils;

    public LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }

    public  LocalDateTime convertToLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }

//    @Test
//    public void currentDateTest() {
//        Assertions.assertEquals(LocalDateTime.now(),utils.currentDateTime());
//    }

    @Test
    public  void convertToLocalDateTimeTest() {
        Date date = new Date();
        LocalDateTime localDateTime = utils.convertToLocalDateTime(date.getTime());
        Assertions.assertNotNull(localDateTime);
    }
}
