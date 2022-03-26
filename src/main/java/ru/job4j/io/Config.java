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
        int id = 0;
        for (String line : toString().split(System.lineSeparator())) {
            id++;
            line = line.trim();
            if (!line.startsWith("#")
                    && line.length() != 0) {
                String[] arg = line.split("=", 2);
                checkKeyValue(id, arg);
                values.put(arg[0].trim(), arg[1].trim());
            }
        }
    }

    private void checkKeyValue(int id, String[] arg) {
        if (arg.length != 2) {
            throw new IllegalArgumentException("Illegal key / value in line:" + id);
        } else if (arg[0].trim().length() == 0) {
            throw new IllegalArgumentException("The key is missing in the line:" + id);
        }  else if (arg[1].trim().length() == 0) {
            throw new IllegalArgumentException("The value is missing in the line:" + id);
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
