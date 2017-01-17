package com.company;

import java.util.Random;

/**
 * Created by olko1016 on 11/9/2016.
 */
public class Massives {

    private final static Random r = new Random();

    public static void main(String[] args) {
        int houseQuantity = getRandomInt(5, 10);
        int flatQuantity = getRandomInt(12, 20);

        int city[][] = new int[houseQuantity][flatQuantity];
        for (int i = 0; i < houseQuantity; i++) {
            for (int j = 0; j < flatQuantity; j++) {
                city[i][j] = getRandomInt(0, 5);
            }
        }

        for (int i = 0; i < houseQuantity; i++) {
            for (int j = 0; j < flatQuantity; j++) {
                System.out.print(city[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static int getRandomInt(int min, int max) {
        return min + r.nextInt(max - min + 1);
    }
}
