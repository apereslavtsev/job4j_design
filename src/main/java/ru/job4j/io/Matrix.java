package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static int[][] multiple(int size) {
        int[][] rsl = new int[size][size];
        for (int row = 1; row <= size; row++) {
            for (int cell = 1; cell <= size; cell++) {
                rsl[row - 1][cell - 1] = row * cell;
            }
        }
        return rsl;
    }

    public static void saveInFile(int[][] matrix, String fileName) {

        StringBuilder writeString = new StringBuilder();
        for (int[] line :  matrix) {
            for (int cell : line) {
                writeString.append(cell + " ");
            }
            writeString.append(System.lineSeparator());
        }
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            out.write(writeString.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        saveInFile(multiple(4), "test4.txt");
    }
}
