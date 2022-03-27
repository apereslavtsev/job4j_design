package ru.job4j.io.duplicates;

import ru.job4j.set.Set;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final HashMap<FileProperty, HashSet<String>> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!duplicates.containsKey(property)) {
            duplicates.put(property, new HashSet<>());
        }
        duplicates.get(property).add(file.toFile().getAbsolutePath());

        return super.visitFile(file, attrs);
    }

    public List<FileProperty> getDuplicatesList() {
        return duplicates.entrySet().stream()
                .filter(f -> f.getValue().size() > 1)
                .flatMap(v -> v.getValue().stream()
                        .map(s -> new FileProperty(v.getKey().getSize(), s)))
                .collect(Collectors.toList());
    }
}
