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
        final B b = new B();
        b.int_field = 3;
        b.printField();
        System.out.println(((A) b).int_field);
    }

    int f1() throws Exception {
        throw new Exception();
    }
}
