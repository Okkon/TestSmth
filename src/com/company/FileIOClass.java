package com.company;

import java.io.*;

/**
 * Created by olko1016 on 10/27/2016.
 */
public class FileIOClass {
    public static void main(String[] args) {
        new FileIOClass().readFile();
    }

    private void readFile() {
        try (
                BufferedReader br = new BufferedReader(new FileReader("input.txt"));
                BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        ) {
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s + ",");
                bw.append(s + ",\n");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
