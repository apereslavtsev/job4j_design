package ru.job4j.io;

import java.io.*;
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
        Scanner scanner = new Scanner(new File(arguments.get("path")))
                .useDelimiter(System.lineSeparator());

             while (scanner.hasNext()) {
                 String line = scanner.next();
            }

            /*while (scanner.hasNext()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }*/

    }

    public static void main(String[] args) throws Exception {
        ArgsName arguments = ArgsName.of(args);
        if (argsValid(arguments)) {
            handle(arguments);
        }
    }

}
