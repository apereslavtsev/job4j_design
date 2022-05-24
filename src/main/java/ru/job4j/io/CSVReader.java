package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static boolean argsValid(ArgsName arguments) {
        StringBuilder exceptionText = new StringBuilder();
        if (!new File(arguments.get("path")).exists()) {
            exceptionText.append("The specified directory does not exist: ")
                    .append(arguments.get("path"))
                    .append(System.lineSeparator());
        }
        if (!"stdout".equals(arguments.get("out"))
                && !new File(new File(new File(arguments.get("out")).getAbsolutePath()).getParent()).exists()) {
            exceptionText.append("Wrong catalog for target: ")
                    .append(arguments.get("out"));
        }
        if (!exceptionText.isEmpty()) {
            throw new IllegalArgumentException(exceptionText.toString());
        }
        return exceptionText.isEmpty();
    }
    public static void handle(ArgsName arguments) throws Exception {
        String ls = System.lineSeparator();
        Scanner scanner = new Scanner(new File(arguments.get("path")))
                .useDelimiter(ls + "|;");
        StringBuilder text = new StringBuilder();

        List<String> filter     = Arrays.asList(arguments.get("filter").split(","));
        List<String> fileColumns = Arrays.asList(scanner.nextLine().split(";"));

        fileColumns.stream()
                .filter(filter::contains)
                .forEach(c -> text.append(c).append(";"));
        text.deleteCharAt(text.length() - 1);
        text.append(ls);

        int i = 0;
        while (scanner.hasNext()) {
            String value = scanner.next();
            if (filter.contains(fileColumns.get(i++))) {
                text.append(value)
                        .append(";");
            }
            if (i == fileColumns.size()) {
                i = 0;
                text.deleteCharAt(text.length() - 1);
                text.append(ls);
            }
        }
        Files.writeString(Paths.get(arguments.get("out")),
                text.toString(), StandardCharsets.UTF_8);
    }
    public static void main(String[] args) throws Exception {
        ArgsName arguments = ArgsName.of(args);
        if (argsValid(arguments)) {
            handle(arguments);
        }
    }

}
