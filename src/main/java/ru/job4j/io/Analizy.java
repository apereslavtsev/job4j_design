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
            /* save in file */
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)
                    ))) {
                String line;
                boolean unavailable = false;
                while ((line = in.readLine()) != null) {
                    /*new file generate logic*/
                    if (!unavailable
                            && (line.startsWith("400")
                            || line.startsWith("500"))) {
                        out.append(line.split(" ", 2)[1])
                                .append(";");
                        unavailable = true;
                    } else if (unavailable
                            && (line.startsWith("200")
                            || line.startsWith("300"))) {
                        out.append(line.split(" ", 2)[1])
                                .append(System.lineSeparator());
                        unavailable = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при записи данных в файл! " + target);
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении данных из файла!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/serverUnavailablePeriods.txt");
        analizy.unavailable("./data/server2.log", "./data/serverUnavailablePeriods2.txt");
    }
}