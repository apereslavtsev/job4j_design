package ru.job4j.io.find;

import ru.job4j.io.ArgsName;
import ru.job4j.io.LogFilter;
import ru.job4j.io.Search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FindByCondition {
    public static boolean argsValid(ArgsName arguments) {
        StringBuilder exceptionText = new StringBuilder();
        if (!new File(arguments.get("d")).exists()) {
            exceptionText.append("The specified directory does not exist: ")
                    .append(arguments.get("d"))
                    .append(System.lineSeparator());
        }
        if (!new File(new File(new File(arguments.get("o")).getAbsolutePath()).getParent()).exists()) {
            exceptionText.append("Wrong catalog for target: ")
                    .append(arguments.get("o"))
                    .append(System.lineSeparator());
        }
         if (!List.of("mask", "name", "regex")
                 .contains(arguments.get("t"))) {
            exceptionText.append("Search type should be: mask, name or regex");
        }
        if (!exceptionText.isEmpty()) {
            throw new IllegalArgumentException(exceptionText.toString());
        }
        return exceptionText.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        ArgsName arguments = ArgsName.of(args);
        if (argsValid(arguments)) {
                Path start = Paths.get(arguments.get("d"));
                Predicate<Path> condition = p -> p.toFile().getName()
                        .equals(arguments.get("n"));

                String conditionType = arguments.get("t");
                if ("mask".equals(conditionType)) {
                    condition = p -> p.toFile().getName()
                            .matches(getRegexFromFileMask(arguments.get("n")));
                } else if ("regex".equals(conditionType)) {
                    condition = p -> p.toFile().getName()
                            .matches(arguments.get("n"));
                }
            List<String> fileList = Search.search(start, condition).stream()
                    .map(p -> p.toFile().getAbsolutePath())
                    .collect(Collectors.toList());
            LogFilter.save(fileList, arguments.get("o"));
        }
    }
    private static String getRegexFromFileMask(String fileMask) {
        String regexStr = fileMask.replace("?", ".{1}");
        regexStr = regexStr.replace("*", ".*");
        return regexStr;
    }

}
