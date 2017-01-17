package com.company;

public class Test {


    public static void main(String[] args) {
        String s1 = "v10";
        String s2 = "v30";
        String s3 = "v60";
        WeightSelector<String> weightSelector = new WeightSelector<String>()
                .addItem(s1, 10)
                .addItem(s2, 30)
                .addItem(s3, 60);
        weightSelector.showInfo();
        for (int i = 0; i < 1000; i++) {
            String select = weightSelector.select();
        }
        weightSelector.showStatistics();
        f1(new Object());
    }

    public static int f1(Object object) {
        Class<String> aClass = String.class;
        System.out.println(aClass.isInstance(""));
        return 1;
    }

    public static int f1(String object) {
        return 2;
    }
}