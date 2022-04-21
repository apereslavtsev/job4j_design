package ru.job4j.io;
import java.util.HashMap;
import java.util.Map;


public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException("The value is missing!");
        }
        return value;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed!");
        }
        for (int i = 0; i < args.length; i++) {
            String[] arg = args[i].split("=", 2);
            if (args[i].matches("-(.+)=(.+)")) {
                values.put(arg[0].trim().substring(1),
                        arg[1].trim());
            } else {
                showArgumentException(i, arg);
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void showArgumentException(int id, String[] arg) {
        String exceptionText;
        if (arg.length != 2) {
            exceptionText = "Illegal key / value in line:" + id;
        } else if (arg[0].trim().length() == 0) {
            exceptionText = "The key is missing in the line:" + id;
        }  else if (arg[1].trim().length() == 0) {
            exceptionText = "The value is missing in the line:" + id;
        }  else {
            exceptionText = "The key / value in the line:" + id
                    + ", not satisfying the template: -key=value";
        }
        throw new IllegalArgumentException(exceptionText);
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
