package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        /* file read*/
        List<String> fileData = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            fileData = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении данных из файла!");
            e.printStackTrace();
        }
        /* work logic*/
        StringBuilder text = new StringBuilder();
        boolean unavailable = false;
        for (String line : fileData) {
            if (!unavailable
            && (line.startsWith("400")
                    || line.startsWith("500"))) {
                text.append(line.split(" ", 2)[1])
                        .append(";");
                unavailable = true;
            } else if (unavailable
                    && (line.startsWith("200")
                    || line.startsWith("300"))) {
                text.append(line.split(" ", 2)[1])
                        .append(System.lineSeparator());
                unavailable = false;
            }
        }
        /* save in file */
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
                out.print(text);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл! " + target);
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/serverUnavailablePeriods.txt");
        analizy.unavailable("./data/server2.log", "./data/serverUnavailablePeriods2.txt");
    }
}