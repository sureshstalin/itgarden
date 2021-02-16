package com.test;

import org.apache.commons.lang3.StringUtils;

public class FirstSample {

    public static void main(String[] args) {

        String data = "Suresh,Kesavan";

        System.out.println(StringUtils.reverse(data));
        System.out.println(StringUtils.reverseDelimited(data,','));
    }
}
