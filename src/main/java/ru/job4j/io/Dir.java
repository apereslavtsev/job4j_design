package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void showDirectoryFiles(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.printf("name: %1$s ; size: %2$s%n", subfile.getAbsolutePath(), file.length());
            if (subfile.isDirectory()) {
                showDirectoryFiles(subfile);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        showDirectoryFiles(new File(args[0]));
    }
}
