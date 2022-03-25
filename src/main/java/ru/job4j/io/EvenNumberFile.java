package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder out = new StringBuilder();
        for (String line : text.toString().split(System.lineSeparator())) {
            out.append(line)
                    .append(" is ")
                    .append(Integer.parseInt(line) % 2 == 0 ? "even" : "not even")
                    .append(System.lineSeparator());
        }
        System.out.println(out);
    }
}
