package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    private final List<String> logList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
       // while ( )


    }

    private void saveLog() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            logList.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            rsl = in.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении данных из файла!");
            e.printStackTrace();
        }
        return rsl;
    }

    private static ArgsName validArgs(String[] args) {
        ArgsName arguments = ArgsName.of(args);
        if (!new File(new File(new File(arguments.get("a")).getAbsolutePath()).getParent()).exists()) {
            throw new IllegalArgumentException("Wrong target catalog!");
        }
        if (!new File(arguments.get("a")).exists()) {
            throw new IllegalArgumentException("Wrong filename with answers!");
        }
        return arguments;
    }

    public static void main(String[] args) {
        ArgsName arguments = validArgs(args);
        ConsoleChat cc = new ConsoleChat(arguments.get("l"), arguments.get("a"));
        cc.run();
    }
}
