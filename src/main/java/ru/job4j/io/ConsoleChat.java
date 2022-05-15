package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
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
        checkArgs();
    }

    private void checkArgs() {
        if (!new File(new File(new File(path).getAbsolutePath()).getParent()).exists()) {
            throw new IllegalArgumentException("Wrong target catalog!");
        }
        if (!new File(this.botAnswers).exists()) {
            throw new IllegalArgumentException("Wrong filename with answers!");
        }
    }

    private void saveLog() {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            logList.forEach(pw::println);
            logList.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            rsl = in.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении данных из файла!");
            e.printStackTrace();
        }
        return rsl;
    }

    public void run() {
        String userQuestion = "";
        Scanner scanner = new Scanner(System.in);
        List<String> phrases = readPhrases();
        Random random = new Random();
        boolean stopMode = false;

        while (!OUT.equals(userQuestion)) {
            if (!stopMode) {
                String botAnswer = phrases.get(random.nextInt(phrases.size()));
                logList.add(botAnswer);
                System.out.println(botAnswer);
            }
            userQuestion = scanner.nextLine();
            logList.add(userQuestion);
            if (!stopMode
                    && STOP.equals(userQuestion)) {
                stopMode = true;
                saveLog();
            } else if (CONTINUE.equals(userQuestion)) {
                stopMode = false;
                saveLog();
            }
        }
        saveLog();
    }

    public static void main(String[] args) {
        ArgsName arguments = ArgsName.of(args);
        ConsoleChat cc = new ConsoleChat(arguments.get("l"), arguments.get("a"));
        cc.run();
    }
}
