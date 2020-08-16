package com.itgarden.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {


    public static boolean isValidLetters(String name) {
        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public static Timestamp getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
