package com.company;

import com.company.castingWithClasses.A;
import sun.reflect.Reflection;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Created by olko1016 on 11/19/2016.
 */
public class TestSmth {
    String telephone1;
    String telephone2;
    static String telephone3 = "123";


    public static void main(String[] args) {
        System.out.println(6 & 7);
        System.out.println(5 & 10);
        bitExperiments(-4);
    }

    private static void bitExperiments(int k) {
        System.out.println("----------STARTING EXPERIMENTS WITH NUMBER = " + k);
        System.out.println("Integer.toBinaryString(k) = " + Integer.toBinaryString(k));
        printInBinary("k >>  1", k >> 1);
        printInBinary("k >>> 1", k >>> 1);
        printInBinary("~k", ~k);
    }

    private static void printInBinary(String format, int k) {
        final String binary = Integer.toBinaryString(k);
        System.out.println(String.format(format + " = %s, binary = %s, bytes = %s", k, binary, binary.length()));
    }


    private static void f2() {
        String s = null;
        System.out.println(s.split(","));
        Class callerClass = Reflection.getCallerClass();
        System.out.println(callerClass);
    }

    private static void f1() {
        SimpleDateFormat monthYearFormat = new SimpleDateFormat("MM/yyyy");
        System.out.println(monthYearFormat.format(new Date()));

        A a = new A();
        A a2 = a;
        List<A> list = Arrays.asList(a, new A());
        System.out.println(list);

    }

    public static BigDecimal parseAmount(String amount) {
        amount = amount.replace("$", "").replaceAll("-$", "0").trim();
        if (!(Double.isNaN(Double.parseDouble(amount)))) {
            return new BigDecimal(amount).setScale(2, ROUND_HALF_UP);
        } else {
            throw new NumberFormatException();
        }
    }
}
