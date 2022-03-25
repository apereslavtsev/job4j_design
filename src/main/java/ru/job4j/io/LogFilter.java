package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String fileName) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            rsl = in.lines()
                    .filter(new Predicate<String>() {
                        @Override
                        public boolean test(String s) {
                            boolean rsl = false;
                            String[] words = s.split(" ");
                            if ("404".equals(words[words.length - 2])) {
                                rsl = true;
                            }
                            return rsl;
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
    }

}
