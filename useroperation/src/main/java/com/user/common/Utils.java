package com.user.common;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class Utils {

    public  LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }

    public  LocalDateTime convertToLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }
}
