package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegExpTest {
    public static void main(String[] args) throws IOException {
        String regexPattern = "^\\$\\d+([.][0-9]+)?$";
        Pattern pattern = Pattern.compile(regexPattern);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Put in a string. Pattern = " + regexPattern);
        System.out.println("Press q to quit");

        String s = null;
        while (!"q".equals(s)) {
            s = reader.readLine();
            Matcher matcher = pattern.matcher(s);
            System.out.println(matcher.matches());
        }

        System.out.println(String.format("testing format %s and %s", 1, "123"));
        ;


    }
}
