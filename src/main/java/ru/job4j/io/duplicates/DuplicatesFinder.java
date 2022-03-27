package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("./");
        DuplicatesVisitor duplicates = new DuplicatesVisitor();
        Files.walkFileTree(start, duplicates);
        duplicates.getDuplicatesList().forEach(System.out::println);
    }
}
