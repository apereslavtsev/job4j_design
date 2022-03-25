package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        for (String line : toString().split(System.lineSeparator())) {
            line = line.trim();
            if (!line.startsWith("#")
                    && line.length() != 0) {
                String[] arg = line.split("=");
                if (arg.length != 2
                || arg[0].trim().length() == 0
                || arg[1].trim().length() == 0) {
                    throw new IllegalArgumentException();
                }
                values.put(arg[0].trim(), arg[1].trim());
            }
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public String value(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return value;
    }
}