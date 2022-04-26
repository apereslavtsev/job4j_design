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
        if (argsValid(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    private static boolean argsValid(String[] args) {
        StringBuilder exceptionText = new StringBuilder();
        if (args[0].length() == 0
            || !new File(args[0]).exists()) {

            exceptionText.append("Wrong catalog in first argument: " + args[0])
                    .append(System.lineSeparator());
        }
        if (args[1].length() == 0
                || args[1].charAt(0) != '.') {
            exceptionText.append("Second argument must start with a symbol: '.'");
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