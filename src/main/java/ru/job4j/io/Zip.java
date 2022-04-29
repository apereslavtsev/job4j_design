package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> listSources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : listSources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        ArgsName arguments = validArgs(args);
        Path start = Paths.get(arguments.get("d"));
        Predicate<Path> condition = path -> !path.toFile().getName().endsWith(arguments.get("e"));
        List<File> fileList = Search.search(start, condition).stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        Zip zip = new Zip();
        zip.packFiles(fileList,
                new File(arguments.get("o")));
    }
    private static ArgsName validArgs(String[] args) {
        ArgsName arguments = ArgsName.of(args);
        Search.argsValid(arguments.get("d"), arguments.get("e"));
        File target = new File(arguments.get("o"));
        if (!new File(new File(target.getAbsolutePath()).getParent()).exists()) {
            throw new IllegalArgumentException("Wrong target catalog!");
        }
        return arguments;
    }
}
