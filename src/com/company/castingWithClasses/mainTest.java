package com.company.castingWithClasses;

import java.util.ArrayList;

/**
 * Created by olko1016 on 12/26/2016.
 */
public class mainTest {
    private final ArrayList<String> list = new ArrayList<String>() {{
        add(s1);
        add(s2);
    }};

    private final String s1 = new String("1");
    private final String s2 = "1";

    private final ArrayList<Integer> listI = new ArrayList<Integer>() {{
        add(bi1);
        add(bi2);
    }};

    private final int bi1 = new Integer("1");
    private final Integer bi2 = 1;

    public static void main(String[] args) {
        mainTest mainTest = new mainTest();
        System.out.println(mainTest.list);
        System.out.println(mainTest.listI);


        B a = (B) new A();
        A b = new B();
        Number number = a.m1(1);
        Integer integer = a.m2(1);
    }

    int f1() throws Exception {
        throw new Exception();
    }
}
