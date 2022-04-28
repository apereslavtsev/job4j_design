package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("2 arguments are expected");
        }
        if (argsValid(args[0], args[1])) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static boolean argsValid(String catalog, String fileExtension) {
        StringBuilder exceptionText = new StringBuilder();
        if (!new File(catalog).exists()) {

            exceptionText.append("The specified directory does not exist: ")
                    .append(catalog)
                    .append(System.lineSeparator());
        }
        if (fileExtension.charAt(0) != '.') {
            exceptionText.append("File extension must start with a symbol: '.'");
        }
        if (!exceptionText.isEmpty()) {
            throw new IllegalArgumentException(exceptionText.toString());
        }
        return exceptionText.isEmpty();
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        FileSearch searcher = new FileSearch(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}