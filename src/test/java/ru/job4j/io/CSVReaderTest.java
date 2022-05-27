package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

public class CSVReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenFilterTwoColumns() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data, StandardCharsets.UTF_8);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath(), StandardCharsets.UTF_8));
    }
    @Test
    public void whenFilterTreeColumns() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age,education"
        });
        Files.writeString(file.toPath(), data, StandardCharsets.UTF_8);
        String expected = String.join(
                System.lineSeparator(),
                "name;age;education",
                "Tom;20;Bachelor",
                "Jack;25;Undergraduate",
                "William;30;Secondary special"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath(), StandardCharsets.UTF_8));
    }
    @Test
    public void whenFilterTwoColumnsAndStdout() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=stdout", "-filter=name,age"
        });
        Files.writeString(file.toPath(), data, StandardCharsets.UTF_8);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, outContent.toString());
        System.setOut(System.out);
    }

}