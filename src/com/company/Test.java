package com.company;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Test {
    private InnerTestClass innerTestClass = new InnerTestClass(8);
    private int a = 1;
    private boolean b = true;
    private String s = "stringValue";

    //--------------------MAIN METHOD----------------
    public static void main(String[] args) {
        priorityQueueExample();

    }

    private static void priorityQueueExample() {
        final PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> o1.length() > o2.length() ? 1 : -1);
        queue.add("s123");
        queue.add("s12");
        queue.add("s1");
        queue.add("s1234");
        queue.add("s12346");
        queue.add("s12345");
        queue.add("s12347");
        for (String s : queue) {
            System.out.println(s);
        }
        while (queue.size() != 0) {
            System.out.println(queue.remove());
        }
    }

    private static void stringConcatTest() {
        Date start = new Date();
        String info = null;
        for (int i = 0; i < 100000; i++) {
            info = "";
            info = info.concat("ProjectFeeId = ").concat("trabaldi");
            info = info.concat(", ProjectFeeAmount = ").concat("trabaldi");
            info = info.concat(", CurrentMonthInvoicesTemp = ").concat("trabaldi");
            info = info.concat(", CurrentMonthInvoices = ").concat("trabaldi");
            info = info.concat(", AccumulatedInvoices = ").concat("trabaldi");
            info = info.concat(", CurrentMonthRevenue = ").concat("trabaldi");
            info = info.concat(", AccumulatedRevenue = ").concat("trabaldi");
            info = info.concat(", Milestone = ").concat("trabaldi");
            info = info.concat(", Bookings = ").concat("trabaldi");
            info = info.concat(", PreviousMonthBalanceUsd = ").concat("trabaldi");
            info = info.concat(", AccumulatedBookingsUsd = ").concat("trabaldi");
            info = info.concat(", BookingsUsd = ").concat("trabaldi");
            info = info.concat(", ProjectFeeAmountOriginal = ").concat("trabaldi");
            info = info.concat(", AccumulatedBookingsOriginal = ").concat("trabaldi");
            info = info.concat(", BookingsOriginal = ").concat("trabaldi");
            info = info.concat(", NotInvoiced = ").concat("trabaldi");
        }
        System.out.println(new Date().getTime() - start.getTime());

        start = new Date();
        StringBuilder info2 = null;
        for (int i = 0; i < 100000; i++) {
            info2 = new StringBuilder();
            info2 = info2.append("ProjectFeeId = ").append("trabaldi").
                    append(", ProjectFeeAmount = ").append("trabaldi").
                    append(", CurrentMonthInvoicesTemp = ").append("trabaldi").
                    append(", CurrentMonthInvoices = ").append("trabaldi").
                    append(", AccumulatedInvoices = ").append("trabaldi").
                    append(", CurrentMonthRevenue = ").append("trabaldi").
                    append(", AccumulatedRevenue = ").append("trabaldi").
                    append(", Milestone = ").append("trabaldi").
                    append(", Bookings = ").append("trabaldi").
                    append(", PreviousMonthBalanceUsd = ").append("trabaldi").
                    append(", AccumulatedBookingsUsd = ").append("trabaldi").
                    append(", BookingsUsd = ").append("trabaldi").
                    append(", ProjectFeeAmountOriginal = ").append("trabaldi").
                    append(", AccumulatedBookingsOriginal = ").append("trabaldi").
                    append(", BookingsOriginal = ").append("trabaldi").
                    append(", NotInvoiced = ").append("trabaldi");
        }
        System.out.println(new Date().getTime() - start.getTime());
        System.out.println(info);
        System.out.println(info2);
        System.out.println(info2.toString().equals(info.toString()));

    }

    private static void roundModeTest() {
        List<BigDecimal> decimals = Arrays.asList(
                BigDecimal.valueOf(0.24),
                BigDecimal.valueOf(0.25),
                BigDecimal.valueOf(0.26),
                BigDecimal.valueOf(0.35),
                BigDecimal.valueOf(-0.24),
                BigDecimal.valueOf(-0.25),
                BigDecimal.valueOf(-0.26),
                BigDecimal.valueOf(-0.35)
        );

        for (BigDecimal decimal : decimals) {
            System.out.println(decimal.setScale(2, RoundingMode.UNNECESSARY) + "--------");
            for (RoundingMode mode : RoundingMode.values()) {
                if (RoundingMode.UNNECESSARY != mode) {
                    System.out.println(mode + " : " + decimal.setScale(1, mode));
                }
            }
        }
    }


    public static void checkIfNullCanCallMethod() {
        System.out.println("It can!");
    }

    private static void emptyIteratorTest() {
        List<String> strings = new ArrayList<>();
        System.out.println(strings.iterator().next());
    }

    private static void nullCastTest() {
        Test test = new Test();
        test.showFields();
        ((Test) null).checkIfNullCanCallMethod();
        test = null;
        test.checkIfNullCanCallMethod();
    }

    private void showFields() {
        for (Field field : this.getClass().getDeclaredFields()) {
            Object value = null;
            try {
                value = field.get(this);
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
            System.out.println(field + " : " + value);
        }
    }

    private Collection<String> f6() {
        return null;
    }

    private void f5() {
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.SECOND, 1);
        Date time2 = instance.getTime();
        System.out.println(date);
        System.out.println(time2);

        System.out.println(date.compareTo(time2));
        System.out.println(date.equals(time2));
    }

    private void f4() {
        BigDecimal a1 = BigDecimal.ONE, a2 = BigDecimal.ZERO, a3 = null;
        setZerosIfNull(a1);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
    }

    private void setZerosIfNull(BigDecimal... fields) {
        for (BigDecimal field : fields) {
            if (field == null) {
                field = BigDecimal.ZERO;
            }
        }
    }

    private static void f3() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        Iterator<Integer> iterator = integerList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.remove();
            }
        }
        Iterator<Integer> iterator1 = integerList.iterator();
        while (iterator1.hasNext()) {
            Integer next = iterator1.next();
            System.out.println(next);
        }
    }

    private static void f2() {
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
    }

    public static String f1(Object object) {
        Class<String> aClass = String.class;
        System.out.println(aClass.isInstance(object));
        return "Object";
    }

    /*public static String f1(String object) {
        Class<String> aClass = String.class;
        System.out.println(aClass.isInstance(object));
        return "String";
    }*/

    public static String f1(Number object) {
        Class<Number> aClass = Number.class;
        System.out.println(aClass.isInstance(object));
        return "Number";
    }

    public static String f1(Integer object) {
        Class<Integer> aClass = Integer.class;
        System.out.println(aClass.isInstance(object));
        return "Integer";
    }

    private class InnerTestClass {
        int k = 5;
        String s = "Inner class string";

        public InnerTestClass(int k) {
            this.k = k;
        }
    }
}